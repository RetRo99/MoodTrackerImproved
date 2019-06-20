package com.example.moodtrackerimproved.ViewModel

import androidx.test.core.app.ApplicationProvider
import com.example.moodtrackerimproved.Logic.MoodKeeper
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityViewModelTest {

    private lateinit var model:MainActivityViewModel

    private lateinit var moodKeeper: MoodKeeper

    @Before
    fun setUp() {
        moodKeeper = MoodKeeper(ApplicationProvider.getApplicationContext())
        model = MainActivityViewModel((ApplicationProvider.getApplicationContext()))
    }


    @Test
    fun getMoodOnScreen() {
    }

    @Test
    fun getMoodKeeper() {
    }

    @Test
    fun increment() {
    }

    @Test
    fun decrement() {
    }



    @Test
    fun getCurrentComment() {
    }

    @Test
    fun setCurrentComment() {
    }

    @Test
    fun setCurrentDate() {
    }

    @Test
    fun setCurrentMood() {
        model.setCurrentMood(1)
        assertEquals(1,moodKeeper.getCurrentMood())
        model.setCurrentMood(2)
        assertEquals(2,moodKeeper.getCurrentMood())
        model.setCurrentMood(3)
        assertEquals(3,moodKeeper.getCurrentMood())
        model.setCurrentMood(4)
        assertEquals(4,moodKeeper.getCurrentMood())
        model.setCurrentMood(5)
        assertEquals(5,moodKeeper.getCurrentMood())


    }

    @Test
    fun setCurrentIndex() {
    }

}