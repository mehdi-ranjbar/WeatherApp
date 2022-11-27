package com.example.mehdiweather.MyProject.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mehdiweather.MyProject.Adapter.SavedAdapter
import com.example.mehdiweather.MyProject.Model.WeatherResponse
import com.example.mehdiweather.MyProject.Ui.MainActivity
import com.example.mehdiweather.MyProject.Ui.WeatherViewModel
import com.example.mehdiweather.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved.*
import java.io.Serializable


class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var viewModel: WeatherViewModel
    lateinit var savedAdapter: SavedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        viewModel=(activity as MainActivity).viewModel
        setUpRecyclerView()

        viewModel.getSavedWeather().observe(viewLifecycleOwner , Observer {
            savedAdapter.differ.submitList(it)
        })

        val itemTouchHelperCallBack =object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder, ):Boolean{
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val weather = savedAdapter.differ.currentList[position]
                viewModel.deleteWeather(weather)
                Snackbar.make(view,"weather delete successfully",Snackbar.LENGTH_LONG).apply {
                    setAction("undo"){
                        viewModel.saveWeather(weather)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(rvSaved)
        }
    }


    private fun setUpRecyclerView(){
        savedAdapter= SavedAdapter()
        rvSaved.apply {
            adapter=savedAdapter
        }
    }

}