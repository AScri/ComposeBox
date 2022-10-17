package com.ascri.cleanapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SHARED_PREFS_NAME = "com.ascri.cleanapp.shared_prefs"

val ContextModule = module {

    single {
        buildEncryptedSharedPrefs(androidContext())
    }

}

private fun buildEncryptedSharedPrefs(context: Context): SharedPreferences {
    val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    return EncryptedSharedPreferences.create(
        context,
        SHARED_PREFS_NAME,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}