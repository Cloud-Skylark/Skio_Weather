package com.example.skioweather.api

sealed class NetworkResponse<out T> {
    // T refers to WeatherModel
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()

}