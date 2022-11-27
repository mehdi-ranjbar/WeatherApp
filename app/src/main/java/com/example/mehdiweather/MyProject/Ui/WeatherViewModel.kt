package com.example.mehdiweather.MyProject.Ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mehdiweather.MyProject.Model.ForecastModel.ForecastResponse
import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mehdiweather.MyProject.Repository.WeatherRepository
import com.example.mehdiweather.MyProject.Util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    val currentWeather :MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    val forecastWeather :MutableLiveData<Resource<ForecastResponse>> = MutableLiveData()

    init {
        getCurrentWeather("Esfahan")
        getForecastWeather("Esfahan")
    }

    fun getCurrentWeather (cityName:String) = viewModelScope.launch {
        currentWeather.postValue(Resource.Loading())
        val response = weatherRepository.getCurrentWeather(cityName)
        currentWeather.postValue(handleCurrentWeather(response))
    }

    fun getForecastWeather(cityName: String) = viewModelScope.launch {
        forecastWeather.postValue(Resource.Loading())
        val response = weatherRepository.getForecastWeather(cityName)
        forecastWeather.postValue(handleForecastWeather(response))
    }



    private fun handleCurrentWeather(response : Response<WeatherResponse>) : Resource<WeatherResponse>{
        if (response.isSuccessful){
            response.body().let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleForecastWeather(response: Response<ForecastResponse>): Resource<ForecastResponse>? {
        if (response.isSuccessful){
            response.body().let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveWeather(weatherResponse: WeatherResponse) = viewModelScope.launch {
        weatherRepository.upsert(weatherResponse)
    }

    fun getSavedWeather() = weatherRepository.getAllWeathers()

    fun deleteWeather(weatherResponse: WeatherResponse) = viewModelScope.launch {
        weatherRepository.delete(weatherResponse)
    }




}