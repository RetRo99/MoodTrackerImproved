package com.example.moodtrackerimproved.Logic

import android.content.Context
import com.example.moodtrackerimproved.Model.Day
import com.example.moodtrackerimproved.Model.Mood
import com.example.moodtrackerimproved.R

class MoodBank {


    fun getMoods(): Array<Mood> {
        return arrayOf(
            Mood(
                5,
                R.raw.terrible,
                R.mipmap.smiley_disappointed,
                R.color.faded_red
            ),
            Mood(
                4,
                R.raw.bad,
                R.mipmap.smiley_sad,
                R.color.warm_grey),
            Mood(
                3,
                R.raw.decent,
                R.mipmap.smiley_normal,
                R.color.cornflower_blue_65
            ),
            Mood(
                2,
                R.raw.good,
                R.mipmap.smiley_happy,
                R.color.light_sage),
            Mood(
                1,
                R.raw.great,
                R.mipmap.smiley_super_happy,
                R.color.banana_yellow
            )
        )
    }

    fun getOrderedDays(context: Context): ArrayList<Day> {

        //Getting
        val keeper = MoodKeeper(context)
        val daysInOrder: ArrayList<Day> = ArrayList()
        val moods = getMoods()

        val daysInWeek = arrayOf(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        )

        val daysAgo = arrayOf(
            "One week ago",
            "Six days ago",
            "Five days ago",
            "Four days ago",
            "Three days ago",
            "Two days ago",
            "Yesterday"
        )

        //Getting the index of current day. If we know current day index we also know the index of yesterday
        var indexOfYesterday = daysInWeek.indexOf(keeper.getCurrentDate()) - 1

        daysAgo.forEach {
            if (indexOfYesterday < 0) indexOfYesterday = 6
            daysInOrder.add(
                Day(
                    it,
                    moods.filter { it.moodInt == keeper.getMoodOn(daysInWeek[indexOfYesterday]) }[0],
                    keeper.getCommentOn(daysInWeek[indexOfYesterday]),
                    keeper.getCommentOn(daysInWeek[indexOfYesterday]) != null
                )
            )
            indexOfYesterday--

        }

        return daysInOrder


    }

}