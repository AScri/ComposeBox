package com.ascri.composebox.data.source.local.prefs

import android.content.SharedPreferences

abstract class BasicPref(protected val prefs: SharedPreferences){

    protected fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    protected fun putBoolean(key: String, value: Boolean) {
        prefs.edit()
            .putBoolean(key, value)
            .apply()
    }

    protected fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }

    protected fun putInt(key: String, value: Int) {
        prefs.edit()
            .putInt(key, value)
            .apply()
    }

    protected fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    protected fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    abstract fun reset()
}