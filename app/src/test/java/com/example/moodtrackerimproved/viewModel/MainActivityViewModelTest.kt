package com.example.moodtrackerimproved.viewModel

import androidx.test.core.app.ApplicationProvider
import com.example.moodtrackerimproved.logic.MoodKeeper
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
    fun getCurrentComment() {
        moodKeeper.saveCurrentComment("test123")
        assertEquals("test123",model.getCurrentComment())
    }

    @Test
    fun setCurrentComment() {
        model.setCurrentComment("test123")
        assertEquals("test123", moodKeeper.getCurrentComment())
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


}
