package com.ascri.cleanapp.di

import com.ascri.cleanapp.data.source.local.prefs.LoginPrefs
import org.koin.dsl.module

const val DATABASE_NAME = "contacts_database"

val DataModule = module {
    single {
        LoginPrefs(get())
    }
}