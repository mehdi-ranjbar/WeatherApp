package com.example.mweather.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mehdiweather.MyProject.Db.Converters
import com.example.mehdiweather.MyProject.Model.WeatherResponse


@Database(
    entities = [WeatherResponse::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun getWeatherDao():WeatherDao

    companion object{
        @Volatile
        private var instance:WeatherDatabase?=null
        private val Lock=Any()

        operator fun invoke (context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also{ instance=it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java,"weather_db.db").build()







    }
}