package com.ascri.composebox.di

import com.ascri.composebox.domain.usecase.GetMyProfileUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { GetMyProfileUseCase(get(), get()) }
}