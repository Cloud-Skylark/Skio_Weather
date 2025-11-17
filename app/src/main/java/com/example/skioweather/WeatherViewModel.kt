package com.example.skioweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skioweather.api.Constant
import com.example.skioweather.api.NetworkResponse
import com.example.skioweather.api.RetrofitInstance
import com.example.skioweather.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
           try {
               val response = weatherApi.getWeather(Constant.apikey, city)
               if (response.isSuccessful) {
                   response.body()?.let {
                       _weatherResult.value = NetworkResponse.Success(it)
                   }
               }else {
                   _weatherResult.value = NetworkResponse.Error("Failed to fetch weather data")
               }
           } catch (e : Exception) {
               _weatherResult.value = NetworkResponse.Error("Failed to fetch weather data")
           }
        }

    }

}