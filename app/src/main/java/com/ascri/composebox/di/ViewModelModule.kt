package com.ascri.composebox.di

import com.ascri.composebox.presentation.flow.FirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { FirstViewModel() }
}