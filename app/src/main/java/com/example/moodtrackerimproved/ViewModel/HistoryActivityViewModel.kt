package com.example.moodtrackerimproved.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerimproved.Logic.MoodBank
import com.example.moodtrackerimproved.Model.Day
import com.example.moodtrackerimproved.Model.Mood

class HistoryActivityViewModel(application: Application): AndroidViewModel(application) {

    private val moodBank = MoodBank(application)

    val daysInOrder = MutableLiveData<List<Day>>()



    init {
        daysInOrder.value = moodBank.getOrderedDays()
    }





}