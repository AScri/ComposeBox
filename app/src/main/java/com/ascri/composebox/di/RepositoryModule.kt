package com.ascri.composebox.di

import com.ascri.composebox.data.repository.UserRepositoryImpl
import com.ascri.composebox.presentation.navigation.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}