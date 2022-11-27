package com.example.mehdiweather.MyProject.Fragment

import android.media.session.MediaController
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehdiweather.MyProject.Adapter.ForecastAdapter
import com.example.mehdiweather.MyProject.Model.Weather
import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mehdiweather.MyProject.Ui.MainActivity
import com.example.mehdiweather.MyProject.Ui.WeatherViewModel
import com.example.mehdiweather.MyProject.Util.Resource
import com.example.mehdiweather.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.log

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: WeatherViewModel
    lateinit var foreCastAdapter :ForecastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        viewModel=(activity as MainActivity).viewModel
        setUpRecyclerView()


        edt_search.addTextChangedListener {
            viewModel.getCurrentWeather(it.toString())
            viewModel.getForecastWeather(it.toString())

        }

        viewModel.currentWeather.observe(viewLifecycleOwner, {response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {response ->
                        if (response != null) {
                            fab.setOnClickListener {
                                viewModel.saveWeather(response)
                                Snackbar.make(view,"city saved successfully",Snackbar.LENGTH_SHORT).show()
                            }
//
                            //city name
                            txt_name.text = response.name
                            //city main condition
                            val temp= String.format("%.0f",response.main.temp - 273.15)
                            txt_temp.text = temp +"°C"
                            //city video
                            val dayNight=response.weather[0].icon
                            val main = response.weather[0].main
                            checkCondition(dayNight,main)

                        }
                    }
                }
                is Resource.Error -> {
                    response.message.let {
                        Log.e("asasasasasas" , it.toString())
                    }
                }
            }
        })

        viewModel.forecastWeather.observe(viewLifecycleOwner , Observer{response->
            when(response){
                is Resource.Success -> {
                    hideSpinKit()
                    response.data?.let {forecastResponse ->
                        foreCastAdapter.differ.submitList(forecastResponse.list)
                    }
                }
                is Resource.Error ->{
                    hideSpinKit()
                    response.message.let {
                        Log.e("ForecastEror" , it.toString())
                    }
                }
                is Resource.Loading ->{
                    showSpinKit()
                }
            }
        })

    }

    private fun showSpinKit(){
        spinKit.visibility = View.VISIBLE
        main_layout.visibility = View.INVISIBLE
    }

    private fun hideSpinKit(){
        spinKit.visibility = View.INVISIBLE
        main_layout.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        foreCastAdapter = ForecastAdapter()
        homeRv.apply {
            adapter = foreCastAdapter

        }
    }

    private fun checkCondition(icon:String , main:String){
        if (icon.contains("d") && main.equals("Clouds")){
            playVideo(R.raw.cloudy_day)
        }
        if (icon.contains("n") && main.equals("Clouds")){
            playVideo(R.raw.cloudyn_night)
        }
        if (icon.contains("d") && main.equals("Rain")){
            playVideo(R.raw.rain_day)
        }
        if (icon.contains("") && main.equals("Rain")){
            playVideo(R.raw.rain_night)
        }
        if (icon.contains("d") && main.equals("Fog")){
            playVideo(R.raw.foggy_day)
        }
        if (icon.contains("n") && main.equals("Fog")){
            playVideo(R.raw.foggy_night)
        }
        if (icon.contains("d") && main.equals("Clear")){
            playVideo(R.raw.clear_sky)
        }
        if (icon.contains("n") && main.equals("Clear")){
            playVideo(R.raw.clear_night)
        }
        if (icon.contains("d") && main.equals("Snow")){
            playVideo(R.raw.snow)
        }
    }

    private fun playVideo (videoPath:Int){
        videoView!!.setVideoURI(Uri.parse("android.resource://"
                + context?.packageName + "/" + videoPath))
        videoView.start()
    }

}