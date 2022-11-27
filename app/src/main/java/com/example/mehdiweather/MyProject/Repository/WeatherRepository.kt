package com.example.mehdiweather.MyProject.Repository

import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mweather.Api.RetrofitInstance
import com.example.mweather.Db.WeatherDatabase

class WeatherRepository (
    val db:WeatherDatabase
    ){

    suspend fun getCurrentWeather(cityName:String) =
        RetrofitInstance.api.getCurrentWeather(cityName)

    suspend fun getForecastWeather(cityName: String) =
        RetrofitInstance.api.getForecastWeather(cityName)

    suspend fun upsert(weatherResponse: WeatherResponse) = db.getWeatherDao().upsert(weatherResponse)

    fun getAllWeathers() = db.getWeatherDao().getAllWeathers()

    suspend fun delete(weatherResponse: WeatherResponse) = db.getWeatherDao().delete(weatherResponse)
}