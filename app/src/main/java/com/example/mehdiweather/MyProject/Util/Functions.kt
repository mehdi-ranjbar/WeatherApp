package com.example.mehdiweather.MyProject.Util

import java.text.ParseException
import java.text.SimpleDateFormat

class Functions {

    companion object{

        fun convertDataTime(dateTime: String, inputFormat: String, outputFormat: String): String? {
            val input = SimpleDateFormat(inputFormat)
            val output = SimpleDateFormat(outputFormat)
            try {
                val convert = input.parse(dateTime)
                return output.format(convert)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }
    }
}