package com.example.moodtrackerimproved.Logic

import android.content.Context
import android.content.SharedPreferences
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
    private val currentDay= "current_day"




    //Creating SharedPreferences objects from variables we defined before
    private val sharedPrefComments: SharedPreferences = context.getSharedPreferences(commentsPref, Context.MODE_PRIVATE)
    private val sharedPrefMoods: SharedPreferences = context.getSharedPreferences(moodPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentDay: SharedPreferences = context.getSharedPreferences(dayPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentComment: SharedPreferences =
        context.getSharedPreferences(currentCommentPref, Context.MODE_PRIVATE)
    private val sharedPrefCurrentMood: SharedPreferences =
        context.getSharedPreferences(currentMoodPref, Context.MODE_PRIVATE)



    fun saveCurrentComment(comment:String){
        sharedPrefCurrentComment.edit().putString(currentComment, comment).apply()
    }

    fun getCurrentComment():String?{
        return sharedPrefCurrentComment.getString(currentComment,null)
    }

    fun saveCurrentMood(mood:Int){
        sharedPrefCurrentMood.edit().putInt(currentMood, mood).apply()
    }

    fun getCurrentMood():Int{
      return sharedPrefCurrentMood.getInt(currentMood, 3)
    }

    fun saveDay(){
        sharedPrefComments.edit().putString(getCurrentDate(), getCurrentComment()).apply()
        sharedPrefMoods.edit().putInt(getCurrentDate(), getCurrentMood()).apply()
        setCurrentDate()
    }

    fun getMoodOn(dayInWeek:String):Int{
        return sharedPrefMoods.getInt(dayInWeek, 3)
    }


    fun getCommentOn(dayInWeek:String):String?{
        return  sharedPrefComments.getString(dayInWeek, null)
    }


    fun getCurrentDate(): String?{
        return sharedPrefCurrentDay.getString(currentDay, "Not happening")
    }

     fun setCurrentDate(){


        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        lateinit var today: String

        when (day) {
            Calendar.SUNDAY -> today = "Sunday"
            Calendar.MONDAY -> today = "Monday"
            Calendar.TUESDAY -> today = "Tuesday"
            Calendar.WEDNESDAY -> today = "Wednesday"
            Calendar.THURSDAY -> today = "Thursday"
            Calendar.FRIDAY -> today = "Friday"
            Calendar.SATURDAY -> today = "Saturday"
        }
         sharedPrefCurrentDay.edit().putString(currentDay, today).apply()
         sharedPrefCurrentComment.edit().putString(currentComment,null).apply()
         sharedPrefCurrentMood.edit().putInt(currentMood,3).apply()
     }



}