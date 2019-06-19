package com.example.moodtrackerimproved.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerimproved.Mood.Mood
import com.example.moodtrackerimproved.Mood.MoodBank
import com.example.moodtrackerimproved.SharedPreferences.MoodKeeper

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    //Creating MoodBank object
    private val moodBank = MoodBank()

    //Setting the default started mood
    private  var currentIndex = 3

    //Getting array of all possible moods
    private val moods = moodBank.getMoods()

    //Creating MutableLiveData that we will observe from Activity
    val moodOnScreen = MutableLiveData<Mood>()

    //Getting object that stores Mood object of the last week
    val moodKeeper = MoodKeeper(application)

    //Setting the MutableLiveData to the saved current mood
    init {
        moodOnScreen.value = moods[moodKeeper.getCurrentMood()]
        currentIndex = moodKeeper.getCurrentMood()
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
    private fun setLiveData(index:Int){
        moodOnScreen.value = moods[index]
    }

    fun getCurrentComment():String?{
       return moodKeeper.getCurrentComment()
    }

    fun setCurrentComment(comment:String){
        moodKeeper.saveCurrentComment(comment)
    }

    fun setCurrentDate(){
        moodKeeper.setCurrentDate()
    }

    fun setCurrentMood(mood:Int){
        moodKeeper.saveCurrentMood(mood)
    }


}