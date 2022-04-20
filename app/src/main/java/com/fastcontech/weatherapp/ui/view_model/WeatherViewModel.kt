package com.fastcontech.weatherapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcontech.weatherapp.core.models.Cities
import com.fastcontech.weatherapp.core.models.WeatherEntity
import com.fastcontech.weatherapp.data.network.NetworkRepositories
import com.fastcontech.weatherapp.core.models.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.dsl.module

val weatherViewModel = module {
    factory { WeatherViewModel() }
}

class WeatherViewModel : ViewModel() {
    private var _response = MutableLiveData<Resource<WeatherEntity>>()
    val responseWeather: LiveData<Resource<WeatherEntity>>
        get() = _response

    private var _responseCities = MutableLiveData<Resource<Cities>>()
    val responseCitiesWeather: LiveData<Resource<Cities>>
        get() = _responseCities

    fun getWeather(lon: String, lat: String) {
        println("#GetWeather")
        viewModelScope.launch {
            try {
                val result = async {
                    NetworkRepositories.getWeather(
                        lon,
                        lat
                    )
                }
                if (result.await() != null) {
                    _response.value = Resource.success(result.await())
                } else {
                    _response.value = Resource.error("An error occured", null)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun getCityWeather(cityName: String) {
        println("#GetCityWeather")
        viewModelScope.launch {
            try {
                val result =
                    NetworkRepositories.getCityWeather(
                        cityName
                    )

                if (result != null) {
                    _responseCities.value = Resource.success(result)
                } else {
                    _responseCities.value = Resource.error("An error occurred", null)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }
}