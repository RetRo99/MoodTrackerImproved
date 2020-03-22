package com.example.moodtrackerimproved.logic

import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class MoodBankTest {
     lateinit var bank:MoodBank

    @Before
    fun setUp() {
       bank = MoodBank(ApplicationProvider.getApplicationContext())

    }
    @Test
    fun getMoods() {

        val moods = bank.getMoods()
        assertEquals(1, moods[0].moodInt)
        assertEquals(2, moods[1].moodInt)
        assertEquals(3, moods[2].moodInt)
        assertEquals(4, moods[3].moodInt)
        assertEquals(5, moods[4].moodInt)
    }

    @Test
    fun getOrderedDays() {
        val orderedDays = bank.getOrderedDays()
        assertEquals("One week ago", orderedDays[0].daysAgo)
        assertEquals("Yesterday", orderedDays[6].daysAgo)

    }
}
