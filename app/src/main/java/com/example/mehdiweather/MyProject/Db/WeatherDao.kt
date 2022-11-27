package com.example.mweather.Db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mehdiweather.MyProject.Model.WeatherResponse

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(weather: WeatherResponse):Long

    @Delete
    suspend fun delete(weather: WeatherResponse)

    @Query("SELECT * FROM weather")
    fun getAllWeathers():LiveData<List<WeatherResponse>>
}