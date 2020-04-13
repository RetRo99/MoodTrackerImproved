package com.example.moodtrackerimproved.logic

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerimproved.R
import java.util.*

class MoodKeeper(private val context: Context) {


    //Creating SharedPreferences objects from variables we defined before
    private val sharedPrefComments: SharedPreferences =
        context.getSharedPreferences(COMMENTS_PREF, Context.MODE_PRIVATE)
    private val sharedPrefMoods: SharedPreferences =
        context.getSharedPreferences(MOOD_PREF, Context.MODE_PRIVATE)
    private val sharedPrefCurrentDay: SharedPreferences =
        context.getSharedPreferences(DAY_PREF, Context.MODE_PRIVATE)
    private val sharedPrefCurrentComment: SharedPreferences =
        context.getSharedPreferences(CURRENT_COMMENT_PREF, Context.MODE_PRIVATE)
    private val sharedPrefCurrentMood: SharedPreferences =
        context.getSharedPreferences(CURRENT_MOOD_PREF, Context.MODE_PRIVATE)


    //saving passed string as current comment in shared preferences
    fun saveCurrentComment(comment: String) {
        sharedPrefCurrentComment.edit().putString(CURRENT_COMMENT, comment).apply()
    }

    //returnig the string for current day that is stored in sharedpreferences
    fun getCurrentComment(): String? {
        return sharedPrefCurrentComment.getString(CURRENT_COMMENT, "")
    }

    //Saving the passed int as current mood
    fun saveCurrentMood(mood: Int) {
        sharedPrefCurrentMood.edit().putInt(CURRENT_MOOD, mood).apply()
    }

    //Returning the int that is current mood in shared preferences
    fun getCurrentMood(): Int {
        return sharedPrefCurrentMood.getInt(CURRENT_MOOD, 3)
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
    fun getCommentOn(dayInWeek: String): String {
        return sharedPrefComments.getString(dayInWeek, "") ?: ""
    }


    //Returns the current day in string
    fun getCurrentDate(): String? {
        return createCurrentDayString()
    }

    //Resets the currentdaymood and currentdaycommnet shared preferences
    fun setCurrentDate() {

        sharedPrefCurrentDay.edit().putString(CURRENT_DAY, createCurrentDayString()).apply()
        sharedPrefCurrentComment.edit().putString(CURRENT_COMMENT, "").apply()
        //It shows the mood that i set up as default here
        sharedPrefCurrentMood.edit().putInt(CURRENT_MOOD, 3).apply()
    }

    //returns the value of current day in string format
    fun createCurrentDayString(): String {
        val calendar: Calendar = Calendar.getInstance()

        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> context.getString(R.string.day_sunday)
            Calendar.MONDAY -> context.getString(R.string.day_monday)
            Calendar.TUESDAY -> context.getString(R.string.day_tuesday)
            Calendar.WEDNESDAY -> context.getString(R.string.day_wednesday)
            Calendar.THURSDAY -> context.getString(R.string.day_thursday)
            Calendar.FRIDAY -> context.getString(R.string.day_friday)
            Calendar.SATURDAY -> context.getString(R.string.day_saturday)
            else -> throw  IllegalArgumentException("Unknown day")

        }
    }

    companion object {

        //Assigning variables to later create SharedPreferences objects
        private const val COMMENTS_PREF = "com.example.moodtrackerimproved.logic.comments"
        private const val MOOD_PREF = "com.example.moodtrackerimproved.logic.mood"
        private const val DAY_PREF = "com.example.moodtrackerimproved.logic.currentDayPref"
        private const val CURRENT_MOOD_PREF = "com.example.moodtrackerimproved.logic.currentMoodPref"
        private const val CURRENT_COMMENT_PREF = "com.example.moodtrackerimproved.logic.currentCommentPref"
        private const val CURRENT_COMMENT = "com.example.moodtrackerimproved.logic.currentComment"
        private const val CURRENT_MOOD = "com.example.moodtrackerimproved.logic.currentMood"
        private const val CURRENT_DAY = "com.example.moodtrackerimproved.logic.currentDay"

    }
}
