package com.ascri.cleanapp.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.ascri.cleanapp.BuildConfig
import com.ascri.cleanapp.R
import java.io.*
import java.net.URL

fun InputStream.inputStreamToFile(path: String) {
    use { input ->
        File(path).outputStream().use { input.copyTo(it) }
    }
}

fun OutputStream.bitmapToFile(bitmap: Bitmap) {
    use { output ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
    }
}

fun Context.copyFileFromUri(input: Uri, outputFile: File) {
    contentResolver.openInputStream(input)?.inputStreamToFile(outputFile.absolutePath)
}

fun FragmentActivity.downloadFileFromUrl(link: String, outputFile: File) {
    URL(link).openStream().inputStreamToFile(outputFile.absolutePath)
}

fun Context.writeBitmapToFile(bitmap: Bitmap, outputFile: File) {
    contentResolver.openOutputStream(Uri.fromFile(outputFile))?.bitmapToFile(bitmap)
}

fun File.getProviderUriFromFile(context: Context): Uri {
    return FileProvider.getUriForFile(
        context,
        BuildConfig.APPLICATION_ID + context.getString(R.string.file_provider_name),
        this
    )
}

fun Context.getLogsFolder() = getExternalFilesDir("logs")

fun FragmentActivity.getOrCreateFile(name: String): File {
    val folder = File("${getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
    folder.mkdirs()
    return File(folder, name)
}

fun Context.getOrCreateReactionFile(name: String): File {
    val folder = File("$cacheDir")
    folder.mkdirs()
    return File(folder, name)
}
