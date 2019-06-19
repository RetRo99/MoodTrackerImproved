package com.example.moodtrackerimproved.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.moodtrackerimproved.SharedPreferences.MoodBank

class HistoryActivityViewModel(application: Application): AndroidViewModel(application) {

    private val moodBank = MoodBank()



}