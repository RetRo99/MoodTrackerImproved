package com.example.moodtrackerimproved.SharedPreferences

import com.example.moodtrackerimproved.Model.Mood
import com.example.moodtrackerimproved.R

class MoodBank {


    fun getMoods():Array<Mood> {
        return arrayOf(
            Mood(
                1,
                R.raw.terrible,
                R.mipmap.smiley_disappointed,
                R.color.faded_red
            ),
            Mood(2, R.raw.bad, R.mipmap.smiley_sad, R.color.warm_grey),
            Mood(
                3,
                R.raw.decent,
                R.mipmap.smiley_normal,
                R.color.cornflower_blue_65
            ),
            Mood(4, R.raw.good, R.mipmap.smiley_happy, R.color.light_sage),
            Mood(
                5,
                R.raw.great,
                R.mipmap.smiley_super_happy,
                R.color.banana_yellow
            )
        )
    }

}