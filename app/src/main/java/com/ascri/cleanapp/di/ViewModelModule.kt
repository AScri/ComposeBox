package com.ascri.cleanapp.di

import com.ascri.cleanapp.presentation.main.FirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { FirstViewModel() }
}