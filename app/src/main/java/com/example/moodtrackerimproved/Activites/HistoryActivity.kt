package com.example.moodtrackerimproved.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodtrackerimproved.Model.Day
import com.example.moodtrackerimproved.Model.Mood
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.RecycleView.DayAdapter
import com.example.moodtrackerimproved.ViewModel.HistoryActivityViewModel
import com.example.moodtrackerimproved.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val model:HistoryActivityViewModel = ViewModelProviders.of(this).get(HistoryActivityViewModel::class.java)

        //Creating DisplayMetrics object to get the display Height and Width of screen
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        //Getting the width
       val  width = dm.widthPixels
        //We got the height of the screen with status bar. We must get the size od screen minus status bar
        val height = dm.heightPixels - getStatusBarHeight() + 2

        //getting the adapter passing in the parameters for constructor
        val adapter = DayAdapter(height/7, width)
        //Setting the adapter to recycle view


        val daysInOrderObserver: androidx.lifecycle.Observer<ArrayList<Day>> =
            androidx.lifecycle.Observer { dayInOrder -> adapter.daysInOrder = dayInOrder }

        model.daysInOrder.observe(this, daysInOrderObserver)

        //getting the LinearLayoutManager
        val linearLayoutManager = LinearLayoutManager(this)
        //setting the LinearLayoutManager to the recycle view
        recyclerView.layoutManager = linearLayoutManager

        //Setting the adapter to recycle view
        recyclerView.adapter = adapter



    }




    //Function that returns the size of status bar

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
