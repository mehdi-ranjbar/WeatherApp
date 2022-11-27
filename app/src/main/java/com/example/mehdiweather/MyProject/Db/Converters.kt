package com.example.mehdiweather.MyProject.Db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mehdiweather.MyProject.Model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun fromWeatherList(value: List<Weather>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromClouds(could:Clouds):Int{
        return could.all
    }

    @TypeConverter
    fun toCloud(all:Int):Clouds{
        return Clouds(all)
    }

    @TypeConverter
    fun fromCoord(coord: Coord):Double{
        return coord.lat
    }

    @TypeConverter
    fun toCoord(lat:Double):Coord{
        return Coord(lat,lat)
    }

    @TypeConverter
    fun fromMain(main:Main):Double{
        return main.temp
    }

    @TypeConverter
    fun toMain(temp:Double):Main{
        return Main(temp,temp.toInt(),temp.toInt(),temp.toInt(),temp.toInt(),temp,temp,temp)
    }

    @TypeConverter
    fun fromSys(sys:Sys):String{
        return sys.country
    }

    @TypeConverter
    fun toSys(country:String):Sys{
        return Sys(country,1,1,1,1)
    }

    @TypeConverter
    fun fromSpeed(wind: Wind):Double{
        return wind.speed
    }

    @TypeConverter
    fun toWind(speed:Double):Wind{
        return Wind(1,speed,speed)
    }


}