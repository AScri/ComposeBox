package com.ascri.cleanapp.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun <T> lazyUnsynchronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.showToast(@StringRes text: Int) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.getDrawableQuick(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun Context.getColorQuick(@ColorRes color: Int) =
    ContextCompat.getColor(this, color)

fun Context?.isPermissionGranted(permission: String): Boolean {
    if (this == null) return false
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.addIntentsToList(
    list: MutableList<Intent>,
    intent: Intent
): MutableList<Intent> {
    val resInfo = this.packageManager.queryIntentActivities(intent, 0)
    for (resolveInfo in resInfo) {
        val packageName = resolveInfo.activityInfo.packageName
        val targetedIntent = Intent(intent)
        targetedIntent.setPackage(packageName)
        list.add(targetedIntent)
    }
    return list
}

@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

/**
 * Starting with Android 11,
 * you can directly bring up the app-specific settings page for the location permission only
 */
fun Context.getAppPermissionScreenIntent(): Intent =
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        data = Uri.fromParts("package", packageName, null)
    }

fun Context.goAppPermissionScreen() = startActivity(getAppPermissionScreenIntent())

fun Context.sendEmail(emailTo: String, subject: String, prefilledBody: String) {
    val selectorIntent = Intent(Intent.ACTION_SENDTO)
    val urlString =
        "mailto:" + Uri.encode(emailTo) + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(
            prefilledBody
        )
    selectorIntent.data = Uri.parse(urlString)

    val emailIntent = Intent(Intent.ACTION_SEND)
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTo))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    emailIntent.putExtra(Intent.EXTRA_TEXT, prefilledBody)
    emailIntent.selector = selectorIntent

    startActivity(emailIntent)
}
