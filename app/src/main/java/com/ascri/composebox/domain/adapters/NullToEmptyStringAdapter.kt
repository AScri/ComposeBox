package com.ascri.composebox.domain.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson


class NullToEmptyStringAdapter {
    @ToJson
    fun toJson(value: String?): String? {
        return value
    }

    @FromJson
    fun fromJson(reader: JsonReader): String {
        val result = if (reader.peek() === JsonReader.Token.NULL) {
            reader.nextNull()
        } else {
            reader.nextString()
        }

        return result ?: ""
    }
}
