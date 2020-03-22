package com.example.moodtrackerimproved.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerimproved.logic.MoodBank
import com.example.moodtrackerimproved.logic.MoodKeeper
import com.example.moodtrackerimproved.model.Mood

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    //Creating MoodBank object
    private val moodBank = MoodBank(application)

    //Setting the default started mood
    private var currentIndex: Int = 0
    //Getting array of all possible moods
    private val moods = moodBank.getMoods()

    //Creating MutableLiveData that we will observe from Activity
    val moodOnScreen = MutableLiveData<Mood>()

    //Getting object that stores Mood object of the last week
    private val moodKeeper = MoodKeeper(application)

    //Setting the MutableLiveData to the saved current mood
    init {
        //Basicly it remembers which mood was picked and showed that mood when app is opened
        setCurrentIndex()
    }

    // getting current index from shared preferences
    private fun setCurrentIndex() {
        currentIndex = moodKeeper.getCurrentMood() - 1
        setLiveData(currentIndex)
    }

    //Updates LiveData based on swipe up or swipe down
    fun increment() {
        //We increment the  counter
        currentIndex++

        //We only have 5 mood. If Mood is higher than 5 we go to first mood
        if (currentIndex > 4) {
            currentIndex = 0
            //We set the new mood to LiveData
            setLiveData(currentIndex)
        } else {
            setLiveData(currentIndex)
        }
    }

    //Updates LiveData based on swipe up
    fun decrement() {
        //We decrement the  counter
        currentIndex--
        //We only have 5 mood. If Mood is lower than 1 we go to last mood
        if (currentIndex < 0) {
            currentIndex = 4
            //We set the new mood to LiveData
            setLiveData(currentIndex)
        } else {
            //We set the new mood to LiveData
            setLiveData(currentIndex)


        }
    }

    //Function to set the live data to new mood
    private fun setLiveData(index: Int) {
        moodOnScreen.value = moods[index]
    }

    //Returns the mood from sharedpreferences
    fun getCurrentComment(): String? {
        return moodKeeper.getCurrentComment()
    }

    //Saves the comment in sharedpreferences
    fun setCurrentComment(comment: String) {
        moodKeeper.saveCurrentComment(comment)
    }

    //Saves current day in sharedpreferences
    fun setCurrentDate() {
        moodKeeper.setCurrentDate()
    }

    //Saves the current mood in sharedpreferences
    fun setCurrentMood(mood: Int) {
        moodKeeper.saveCurrentMood(mood)
    }
}
