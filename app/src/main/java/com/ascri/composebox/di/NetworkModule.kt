package com.ascri.composebox.di

import com.ascri.composebox.BuildConfig
import com.ascri.composebox.domain.adapters.NullToEmptyStringAdapter
import com.ascri.composebox.domain.adapters.UserJsonAdapter
import com.ascri.composebox.domain.exception.ErrorHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single { createRetrofit(get(), BuildConfig.BASE_URL, get()) }

    single { createOkHttpClient() }

    single { createMoshiConverterFactory() }

    single { createMoshi() }

    factory { createApiErrorHandler(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}


fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(NullToEmptyStringAdapter())
        .add(UserJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}


fun createApiErrorHandler(moshi: Moshi): ErrorHandler {
    return ErrorHandler(moshi)
}