package com.fastcontech.weatherapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.data.local.repository.DatabaseImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.module

val favouriteViewModel = module {
    factory { FavouriteViewModel() }
}

class FavouriteViewModel : ViewModel() {
    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)
    private var _favourites = MutableLiveData<List<Favourite>>(arrayListOf())
    val favouriteResponse: LiveData<List<Favourite>>
        get() = _favourites

    @OptIn(DelicateCoroutinesApi::class)
    fun getFavouritesLocally(databaseImpl: DatabaseImpl) {
        println("#getCitiesLocally")
        coroutineScope.launch {
            val listLocalResponse: List<Favourite> =
                databaseImpl.getFavouriteCitiesWeather()
            withContext(Dispatchers.Main) {
                listLocalResponse.let { _favourites.value = it }
            }
        }
    }

    fun saveFavouritesLocally(databaseImpl: DatabaseImpl, favourites: List<Favourite>) {
        coroutineScope.launch {
            favourites.forEach {
                databaseImpl.insertFavouriteCityWeather(it)
            }
        }
    }

    fun deleteFavourites(databaseImpl: DatabaseImpl, favourites: List<Favourite>) {
        coroutineScope.launch {
            favourites.forEach {
                databaseImpl.deleteFavouriteCityWeather(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
