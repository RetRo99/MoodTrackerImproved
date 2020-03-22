package com.example.moodtrackerimproved.activities

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.recycleView.DayAdapter
import com.example.moodtrackerimproved.viewModel.HistoryActivityViewModel
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val model: HistoryActivityViewModel =
            ViewModelProviders.of(this).get(HistoryActivityViewModel::class.java)


        mainHistoryLayout.doOnLayout {
            //getting the adapter passing in the parameters for constructor
            val adapter = DayAdapter(mainHistoryLayout.height   / 7, mainHistoryLayout.width)
            //Setting the adapter to recycle view

            model.daysInOrder.observe(this, Observer{daysInOrder ->
                adapter.daysInOrder = daysInOrder
            })
            //getting the LinearLayoutManager
            val linearLayoutManager = LinearLayoutManager(this)
            //setting the LinearLayoutManager to the recycle view
            recyclerView.layoutManager = linearLayoutManager

            //Setting the adapter to recycle view
            recyclerView.adapter = adapter
        }

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
