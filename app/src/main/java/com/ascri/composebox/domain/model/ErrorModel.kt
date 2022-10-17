package com.ascri.composebox.domain.model

import retrofit2.HttpException

/**
 * This class designed to show different types of errors through error status & message
 *
 * */

data class ErrorModel(
    val message: String? = null,
    val code: Int? = null,
    val errorStatus: ErrorStatus,
    val throwable: Throwable,
    val payloadErrorResponse: PayloadErrorResponse? = null,
    val responseBodyString: String? = null
) {

    fun parseErrorBody(): String {
        return (throwable as? HttpException)?.response()?.errorBody()?.string() ?: ""
    }

    fun onEachErrorDescription(onDescription: (description: String)-> Unit){
        payloadErrorResponse?.payload?.forEach { payload ->
           payload.data.forEach { data ->
               onDescription(data.description)
           }
        }
    }

    override fun toString(): String {
        throwable.printStackTrace()
        return errorStatus.errorText
    }

    /**
     * various error status to know what happened if something goes wrong with a repository call
     */
    enum class ErrorStatus(val errorText: String) {
        /**
         * error in connecting to repository (Server or Database)
         */
        NO_CONNECTION("No connection!"),

        /**
         * error in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE("Bad response!"),

        /**
         * server unable to process the request due to incorrect syntax etc
         */
        BAD_REQUEST("Bad request!"),

        /**
         * Time out  error
         */
        TIMEOUT("Time out!"),

        /**
         * no data available in repository
         */
        EMPTY_RESPONSE("Empty response!"),

        /**
         * an unexpected error
         */
        NOT_DEFINED("Not defined!"),

        /**
         * bad credential
         */
        UNAUTHORIZED("Unauthorized!"),

        /**
         * bad credential
         */
        NOT_FOUND("Not found!"),

        /**
         * root coroutine was canceled
         */
        CANCELED("Coroutine canceled!")
    }
}