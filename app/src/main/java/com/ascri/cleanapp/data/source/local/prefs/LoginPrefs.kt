package com.ascri.cleanapp.data.source.local.prefs

import android.content.SharedPreferences

private const val PREF_NEED_LOGIN = "need_login"

class LoginPrefs(prefs: SharedPreferences) : BasicPref(prefs) {
    var isNeedLogin: Boolean
        get() = getBoolean(PREF_NEED_LOGIN, true)
        set(value) {
            putBoolean(PREF_NEED_LOGIN, value)
        }

    override fun reset() {
        prefs.edit()
            .remove(PREF_NEED_LOGIN)
            .apply()
    }
}