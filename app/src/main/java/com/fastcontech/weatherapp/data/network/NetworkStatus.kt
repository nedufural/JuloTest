package com.fastcontech.weatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.koin.dsl.module
import kotlin.math.sin

val networkStatus = module {
    single { NetworkStatus() }
}
class NetworkStatus {

    fun networkStatus(context: Context): Boolean {
        println("#networkStatus Checking network")
        val isConnected: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork
        val activeNetworks: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(networkCapabilities)
        isConnected = when {
            activeNetworks?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            activeNetworks?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            activeNetworks?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
            else -> false
        }
        return isConnected
    }
}
