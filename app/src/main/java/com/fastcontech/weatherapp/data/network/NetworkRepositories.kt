package com.fastcontech.weatherapp.data.network

import NetworkClient
import com.fastcontech.weatherapp.core.models.Cities
import com.fastcontech.weatherapp.core.models.WeatherEntity
import io.ktor.client.features.*
import io.ktor.client.request.get

object NetworkRepositories {
    suspend fun getWeather(lat: String, lon: String): WeatherEntity? {
        println("#getWeather")
        return try {
            NetworkClient.httpClient.use { httpClient ->
                httpClient.get(NetworkConstants.concatWeatherUrl(lat, lon))
            }
        }catch (e: RedirectResponseException){
            null
        }
        catch (e: ClientRequestException){
            null
        }
        catch (e:ServerResponseException){
            null
        }
    }

    suspend fun getCityWeather(city: String): Cities? {
        println("#getCityWeather")
        return try {NetworkClient.citiesWeatherHttp.use { httpClient ->
            httpClient.get(NetworkConstants.getSearchUrl(city))
        }
        }catch (e: RedirectResponseException){
            null
        }
        catch (e: ClientRequestException){
            null
        }
        catch (e:ServerResponseException){
            null
        }
    }

    suspend fun getFavouriteWeather(city: String): Cities? {
        println("#getCityWeather")
        return try {NetworkClient.favouriteHttp.use { httpClient ->
            httpClient.get(NetworkConstants.getSearchUrl(city))
        }
        }catch (e: RedirectResponseException){
            null
        }
        catch (e: ClientRequestException){
            null
        }
        catch (e:ServerResponseException){
            null
        }
    }
}
