package com.ascri.cleanapp.di

import com.ascri.cleanapp.domain.usecase.GetMyProfileUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { GetMyProfileUseCase(get(), get()) }
}