package com.example.mehdiweather.MyProject.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mehdiweather.MyProject.Repository.WeatherRepository
import com.example.mehdiweather.R
import com.example.mweather.Db.WeatherDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        val repository=WeatherRepository(WeatherDatabase(this))
        val viewModelProviderFactory= WeatherViewModelProviderFactory(repository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory).get(WeatherViewModel::class.java)
        setUpNavigationView()
    }


    private fun setUpNavigationView(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

}