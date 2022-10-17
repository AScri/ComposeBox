package com.ascri.composebox.utils.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


object NetManager {

    private val TAG = NetManager::javaClass.name

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    suspend fun isNetworkConnected(context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            if (isOnline(context)) {
                try {
                    val url = URL("https://www.google.com")
                    val urlConnect = (url.openConnection() as HttpURLConnection).apply {
                        connectTimeout = 2000
                        readTimeout = 2000
                    }
                    urlConnect.content
                    true
//                 For Sockets ->
//                 Socket().use { it.connect(InetSocketAddress(BuildConfig.BASE_URL, 5222), 2000)}
//                 true
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                } catch (ex: IOException) {
                    Log.e(TAG, "Error checking internet connection", ex)
                    ex.printStackTrace()
                    false
                }
            } else {
                Log.e(TAG, "No network connection")
                false
            }
        }
    }
}