package com.ascri.composebox.application

import android.app.Application
import com.ascri.composebox.BuildConfig
import com.ascri.composebox.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class ComposeBox : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ComposeBox)
            modules(koinModules)
        }
        timberInit()
    }

    private fun timberInit() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
//                Timber.plant(FirebaseCrashlyticsReportTree())
        }
    }

    private val koinModules = listOf(
        ContextModule,
        ViewModelModule,
        NetworkModule,
        UseCaseModule,
        RepositoryModule,
        ApiModule
    )

    companion object {
        val TAG = ComposeBox::class.java.simpleName
    }
}