package com.solutionsmax.gurukoolmax_v3.core.utils

import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import com.solutionsmax.gurukoolmax_v3.core.extension.connectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class NetworkHandler @Inject constructor(private val context: Context) {
    @Provides
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.connectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}