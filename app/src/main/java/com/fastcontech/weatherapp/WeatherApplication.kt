package com.fastcontech.weatherapp

import android.app.Application
import com.fastcontech.weatherapp.data.network.networkStatus
import com.fastcontech.weatherapp.ui.view_model.favouriteViewModel
import com.fastcontech.weatherapp.ui.view_model.weatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
           androidLogger(Level.NONE) // level can be changed
            androidContext(applicationContext)
            androidFileProperties()
            koin.loadModules(
                listOf(
                    weatherViewModel,
                    favouriteViewModel,
                    networkStatus
                )
            )
        }
    }
}