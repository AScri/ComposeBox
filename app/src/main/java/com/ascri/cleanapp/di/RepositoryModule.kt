package com.ascri.cleanapp.di

import com.ascri.cleanapp.data.repository.UserRepositoryImpl
import com.ascri.cleanapp.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}