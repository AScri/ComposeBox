package com.ascri.composebox.utils.extensions

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity?.hideKeyboard() {
    if (this == null) return
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let { view ->
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Activity.enableSpeaker() {
    (getSystemService(Context.AUDIO_SERVICE) as AudioManager).apply {
        isSpeakerphoneOn = true
        mode = AudioManager.MODE_IN_COMMUNICATION
    }
}

fun Activity.disableSpeaker() {
    (getSystemService(Context.AUDIO_SERVICE) as AudioManager).apply {
        isSpeakerphoneOn = false
        mode = AudioManager.MODE_IN_COMMUNICATION
    }
}

fun Activity.hideSoftKeyboard(view: View) {
    val inputMethodManager: InputMethodManager = getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        view.windowToken, 0
    )
}

fun Activity.showSoftKeyboard(view: View) {
    val inputMethodManager: InputMethodManager = getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}
