package com.example.mehdiweather.MyProject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mehdiweather.R
import kotlinx.android.synthetic.main.city_item.view.*

class SavedAdapter: RecyclerView.Adapter<SavedAdapter.savedAdapterViewHolder>() {

    inner class savedAdapterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<WeatherResponse>(){
        override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse, ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this ,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): savedAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        return savedAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: savedAdapterViewHolder, position: Int) {
        val weatherResponse = differ.currentList[position]
        holder.itemView.apply {
            txt_city_name.text = weatherResponse.name
            txt_city_description.text=weatherResponse.weather[0].description
            val temp = String.format("%.0f" , weatherResponse.main.temp - 273.15)
            txt_city_temp.text = temp + "°C"

        }
    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    
}