package com.ascri.cleanapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayloadErrorResponse(val message: String, val payload: List<Payload>) {

    data class Payload(val field: String, val data: List<Data>)

    data class Data(val description: String, val code: CustomErrorCode)

    enum class CustomErrorCode {
        @Json(name = "not_found")
        NOT_FOUND,

        @Json(name = "invalid")
        INVALID,

        @Json(name = "invalid_phone_number")
        INVALID_PHONE_NUMBER,

        @Json(name = "min_length")
        MIN_LENGTH,

        @Json(name = "max_length")
        MAX_LENGTH,

        @Json(name = "unique")
        UNIQUE,

        @Json(name = "not_a_list")
        NOT_A_LIST,

        @Json(name = "blank")
        BLANK,

        @Json(name = "timeout")
        TIMEOUT,

        @Json(name = "limit")
        LIMIT
    }

    enum class CustomErrorField(val field: String) {
        PHONE_NUMBER("phone_number"),
        INVITED_NUMBER("invited_number"),
        USERNAME("username"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        NAME("name"),
        USER("user")
    }
}