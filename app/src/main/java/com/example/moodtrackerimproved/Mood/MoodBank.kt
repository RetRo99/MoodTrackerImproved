package com.example.moodtrackerimproved.Mood

import com.example.moodtrackerimproved.R

class MoodBank {


    val moods = arrayOf(
        Mood(EnumMood.TERRIBLE, R.raw.terrible,R.mipmap.smiley_disappointed, R.color.faded_red),
        Mood(EnumMood.BAD, R.raw.bad,R.mipmap.smiley_sad, R.color.warm_grey),
        Mood(EnumMood.DECENT, R.raw.decent,R.mipmap.smiley_normal, R.color.cornflower_blue_65),
        Mood(EnumMood.GOOD, R.raw.good,R.mipmap.smiley_happy, R.color.light_sage),
        Mood(EnumMood.GREAT, R.raw.great,R.mipmap.smiley_super_happy, R.color.banana_yellow))

}