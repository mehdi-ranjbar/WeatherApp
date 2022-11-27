package com.example.mehdiweather.MyProject.Model.ForecastModel

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: MutableList<ForecastList>,
    val message: Int
)