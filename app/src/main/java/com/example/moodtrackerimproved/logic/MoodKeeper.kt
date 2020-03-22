package com.example.moodtrackerimproved.logic

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*

class MoodKeeper(context: Context) {

    //Assigning variables to later create SharedPreferences objects
    private val commentsPref = "comments"
    private val moodPref = "mood"
    private val dayPref = "currentDayPref"
    private val currentMoodPref = "currentMoodPref"
    private val currentCommentPref = "currentCommentPref"
    private val currentComment = "current_comment"
    private val currentMood = "current_mood"
    private val currentDay = "current_day"

    //Creating SharedPreferences objects from variables we defined before
    private val sharedPrefComments: SharedPreferences =
        context.getSharedPreferences(commentsPref, Context.MODE_PRIVATE)
    private val sharedPrefMoods: SharedPreferences =
        context.getSharedPreferences(moodPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentDay: SharedPreferences =
        context.getSharedPreferences(dayPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentComment: SharedPreferences =
        context.getSharedPreferences(currentCommentPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentMood: SharedPreferences =
        context.getSharedPreferences(currentMoodPref, Context.MODE_PRIVATE)


    //saving passed string as current comment in shared preferences
    fun saveCurrentComment(comment: String) {
        sharedPrefCurrentComment.edit().putString(currentComment, comment).apply()
    }

    //returnig the string for current day that is stored in sharedpreferences
    fun getCurrentComment(): String? {
        return sharedPrefCurrentComment.getString(currentComment, null)
    }

    //Saving the passed int as current mood
    fun saveCurrentMood(mood: Int) {
        sharedPrefCurrentMood.edit().putInt(currentMood, mood).apply()
    }

    //Returning the int that is current mood in shared preferences
    fun getCurrentMood(): Int {
        return sharedPrefCurrentMood.getInt(currentMood, 3)
    }

    //Saves the current comment and current mood with current day as the holder
    fun saveDay() {
        sharedPrefComments.edit().putString(getCurrentDate(), getCurrentComment()).apply()
        sharedPrefMoods.edit().putInt(getCurrentDate(), getCurrentMood()).apply()
        setCurrentDate()
    }

    //returns mood on a specific day
    fun getMoodOn(dayInWeek: String): Int {
        return sharedPrefMoods.getInt(dayInWeek, 3)
    }

    //returns comment on a specific day
    fun getCommentOn(dayInWeek: String): String? {
        return sharedPrefComments.getString(dayInWeek, null)
    }


    //Returns the current day in string
    fun getCurrentDate(): String? {
        return createCurrentDayString()
    }

    //Resets the currentdaymood and currentdaycommnet shared preferences
    fun setCurrentDate() {

        sharedPrefCurrentDay.edit().putString(currentDay, createCurrentDayString()).apply()
        sharedPrefCurrentComment.edit().putString(currentComment, null).apply()
        //It shows the mood that i set up as default here
        sharedPrefCurrentMood.edit().putInt(currentMood, 3).apply()
    }

    //returns the value of current day in string format
    fun createCurrentDayString(): String {
        val calendar: Calendar = Calendar.getInstance()

        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> throw  IllegalArgumentException("Unknown day")

        }
    }
}
