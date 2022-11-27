package com.example.mweather.Api

import com.example.mehdiweather.MyProject.Model.ForecastModel.ForecastResponse
import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mweather.Util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q")
        cityName:String = "esfahan",
        @Query("appid")
        apiKey:String=API_KEY
    ):Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("q")
        cityName: String="shahr-e kord",
        @Query("appid")
        apiKey: String= API_KEY
    ):Response<ForecastResponse>
}