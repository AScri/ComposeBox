package com.ascri.composebox.di

import com.ascri.composebox.data.source.remote.api.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiModule = module {
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
}