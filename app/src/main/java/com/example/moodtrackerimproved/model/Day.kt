package com.example.moodtrackerimproved.model

//Creating a data class to store days to use for history activity
data class Day(val daysAgo: String, val mood: Mood, val comment: String?, val hasComment: Boolean)
