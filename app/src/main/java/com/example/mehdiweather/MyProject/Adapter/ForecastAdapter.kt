package com.example.mehdiweather.MyProject.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mehdiweather.MyProject.Model.ForecastModel.ForecastList
import com.example.mehdiweather.MyProject.Util.Functions
import com.example.mehdiweather.R
import kotlinx.android.synthetic.main.forcast_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<ForecastList>(){
        override fun areItemsTheSame(oldItem: ForecastList, newItem: ForecastList): Boolean {
            return oldItem.weather[0].id == newItem.weather[0].id
        }
        override fun areContentsTheSame(oldItem: ForecastList, newItem: ForecastList): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forcast_item,parent,false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val weatherForecastRes = differ.currentList[position]
        holder.itemView.apply {
            //description
            txt_forecast_main.text = weatherForecastRes.weather[0].description
            //temperature
            val temp = String.format("%.0f" , weatherForecastRes.main.temp - 273.15)
            txt_forecast_temp.text = temp + "°C"

            //icon
            Log.i("iconnnnnn" , weatherForecastRes.weather[0].icon)
            val icon = weatherForecastRes.weather[0].icon
            val url = "https://openweathermap.org/img/wn/"+ icon +"@2x.png"
            Glide.
            with(this).
            load(url).
                    into(img_forecast_icon)

            //date Time
//            val monthName=getAbbreviatedFromDateTime(weatherForecastRes.dt_txt,"yyyy-MM-dd HH:mm:ss","MMMM")
//
            val dayOfWeek=Functions.convertDataTime(weatherForecastRes.dt_txt,"yyyy-MM-dd HH:mm:ss","EEEE")
            val hour = Functions.convertDataTime(weatherForecastRes.dt_txt ,"yyyy-MM-dd HH:mm:ss" , "EEEE, HH:mm" )
            txt_forecast_dt.text = hour




        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}