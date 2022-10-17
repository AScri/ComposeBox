package com.ascri.cleanapp.di

import com.ascri.cleanapp.data.source.remote.api.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiModule = module {
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
}