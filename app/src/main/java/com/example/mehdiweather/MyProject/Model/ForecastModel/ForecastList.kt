package com.example.mehdiweather.MyProject.Model.ForecastModel

data class ForecastList (
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: MutableList<Weather>,
    val wind: Wind
)