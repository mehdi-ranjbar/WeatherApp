package com.example.mehdiweather.MyProject.Ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mehdiweather.MyProject.Repository.WeatherRepository

class WeatherViewModelProviderFactory(val weatherRepository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}