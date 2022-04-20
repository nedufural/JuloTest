package com.fastcontech.weatherapp.core.models

import com.fastcontech.weatherapp.core.models.ViewConstants.Companion.EMPTY_STRING

data class Resource<out T>(val status: Status, val data: T?, val message: String? = EMPTY_STRING) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
