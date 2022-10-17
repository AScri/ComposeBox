package com.ascri.composebox.domain.exception

import com.ascri.composebox.domain.model.ErrorModel
import com.ascri.composebox.domain.model.PayloadErrorResponse
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns [ErrorModel]
 *
 * */
class ErrorHandler(
    private val moshi: Moshi
) {

    suspend fun refreshTokens() {}

    fun traceErrorException(throwable: Throwable): ErrorModel =
        when (throwable) {
            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                val responseBodyString = throwable.bodyString()
                val payloadError = responseBodyString?.let { getPayloadError(it) }
                when (val code = throwable.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorModel( // handle UNAUTHORIZED situation (when token expired)
                        throwable.message(),
                        code,
                        ErrorModel.ErrorStatus.UNAUTHORIZED,
                        throwable,
                        payloadError,
                        responseBodyString
                    )
                    HttpURLConnection.HTTP_BAD_REQUEST -> ErrorModel(
                        throwable.message(),
                        code,
                        ErrorModel.ErrorStatus.BAD_REQUEST,
                        throwable,
                        payloadError,
                        responseBodyString
                    )
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorModel(
                        throwable.message(),
                        code,
                        ErrorModel.ErrorStatus.NOT_FOUND,
                        throwable,
                        payloadError,
                        responseBodyString
                    )
                    else -> throwable.getHttpError(payloadError, responseBodyString)
                }
            }
            is SocketTimeoutException -> ErrorModel( // handle api call timeout error
                message = throwable.message,
                errorStatus = ErrorModel.ErrorStatus.TIMEOUT,
                throwable = throwable
            )
            is IOException -> ErrorModel( // handle connection error
                message = throwable.message,
                errorStatus = ErrorModel.ErrorStatus.NO_CONNECTION,
                throwable = throwable
            )
            is CancellationException -> {
                ErrorModel(
                    message = throwable.message,
                    errorStatus = ErrorModel.ErrorStatus.CANCELED,
                    throwable = throwable
                )
            }
            else -> ErrorModel(
                message = ErrorModel.ErrorStatus.NOT_DEFINED.errorText,
                errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED,
                throwable = throwable
            )
        }

    /**
     * attempts to parse http response body and get error data from it
     *
     * body is a retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun HttpException.getHttpError(
        payloadError: PayloadErrorResponse?,
        responseBodyString: String?
    ): ErrorModel =
        try {
            // use response body to get error detail
            ErrorModel(
                bodyString(),
                code(),
                ErrorModel.ErrorStatus.BAD_RESPONSE,
                this,
                payloadError,
                responseBodyString
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(
                message = e.message,
                errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED,
                throwable = this,
                payloadErrorResponse = payloadError,
                responseBodyString = responseBodyString
            )
        }

    private fun HttpException.bodyString(): String? =
        try {
            response()?.errorBody()?.string()
        } catch (e: Throwable) {
            null
        }

    private fun getPayloadError(responseBodyString: String): PayloadErrorResponse? =
        try {
            val adapter = moshi.adapter(PayloadErrorResponse::class.java)
            adapter.fromJson(responseBodyString)
        } catch (e: Exception) {
            null
        }

}