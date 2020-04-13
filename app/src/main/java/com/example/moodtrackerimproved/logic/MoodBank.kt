package com.example.moodtrackerimproved.logic

import android.content.Context
import android.widget.Toast
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.model.Day
import com.example.moodtrackerimproved.model.Mood

class MoodBank(private val context: Context) {


    fun getMoods(): Array<Mood> {
        return arrayOf(
            Mood(
                1,
                R.raw.great,
                R.drawable.smiley_super_happy,
                R.color.banana_yellow
            ),
            Mood(
                2,
                R.raw.good,
                R.drawable.smiley_happy,
                R.color.light_sage
            ),
            Mood(
                3,
                R.raw.decent,
                R.drawable.smiley_normal,
                R.color.cornflower_blue_65
            ),
            Mood(
                4,
                R.raw.bad,
                R.drawable.smiley_sad,
                R.color.warm_grey
            ),
            Mood(
                5,
                R.raw.terrible,
                R.drawable.smiley_disappointed,
                R.color.faded_red
            )

        )
    }

    fun getOrderedDays(): List<Day> {

        //Getting
        val keeper = MoodKeeper(context)
        val daysInOrder: ArrayList<Day> = ArrayList()
        val moods = getMoods()

        val daysInWeek = arrayOf(
            context.getString(R.string.day_monday), context.getString(R.string.day_tuesday), context.getString(
                            R.string.day_wednesday), context.getString(R.string.day_thursday), context.getString(
                                            R.string.day_friday), context.getString(
                                            R.string.day_saturday), context.getString(R.string.day_sunday)
        )

        val daysAgo = arrayOf(
            context.getString(R.string.yesterday),
            context.getString(R.string.two_days_ago),
            context.getString(R.string.three_days_ago),
            context.getString(R.string.four_days_ago),
            context.getString(R.string.five_days_ago),
            context.getString(R.string.six_days_ago),
            context.getString(R.string.a_week_ago)
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
                    keeper.getCommentOn(daysInWeek[indexOfYesterday]).isNotEmpty()
                )
            )
            indexOfYesterday--

        }

        return daysInOrder.reversed()


    }

}
