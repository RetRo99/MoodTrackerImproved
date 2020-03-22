package com.example.moodtrackerimproved.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerimproved.logic.MoodBank
import com.example.moodtrackerimproved.model.Day

class HistoryActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moodBank = MoodBank(application)

    val daysInOrder = MutableLiveData<List<Day>>()


    init {
        daysInOrder.value = moodBank.getOrderedDays()
    }


}
