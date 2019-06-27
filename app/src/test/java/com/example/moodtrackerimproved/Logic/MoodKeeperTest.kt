package com.example.moodtrackerimproved.Logic

import androidx.test.core.app.ApplicationProvider
import com.example.moodtrackerimproved.ViewModel.MainActivityViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)

class MoodKeeperTest {



    private lateinit var moodKeeper: MoodKeeper
    private lateinit var moodKeeperMock:MoodKeeper

    @Before
    fun setUp() {
        moodKeeper = MoodKeeper(ApplicationProvider.getApplicationContext())

         moodKeeperMock = Mockito.spy(moodKeeper)
        Mockito.doReturn("Sunday").`when`(moodKeeperMock).createCurrentDayString()

    }
    @Test
    fun saveCurrentComment() {
        moodKeeper.saveCurrentComment("test123")
        assertEquals("test123",moodKeeper.getCurrentComment())
    }

    @Test
    fun getCurrentComment() {
        moodKeeper.saveCurrentComment("test123")
        assertEquals("test123",moodKeeper.getCurrentComment())
    }

    @Test
    fun saveCurrentMood() {
        moodKeeper.saveCurrentMood(5)
        assertEquals(5,  moodKeeper.getCurrentMood())
    }

    @Test
    fun getCurrentMood() {
        moodKeeper.saveCurrentMood(5)
        assertEquals(5,  moodKeeper.getCurrentMood())
    }



    @Test
    fun getMoodOn() {

        moodKeeperMock.saveCurrentMood(5)
        moodKeeperMock.saveDay()
        assertEquals(5,moodKeeperMock.getMoodOn("Sunday"))

    }

    @Test
    fun getCommentOn() {
        moodKeeperMock.saveCurrentComment("test123")
        moodKeeperMock.saveDay()
        assertEquals("test123",moodKeeperMock.getCommentOn("Sunday"))
    }

    @Test
    fun getCurrentDate() {
        assertEquals("Sunday", moodKeeperMock.getCurrentDate())


    }

    @Test
    fun setCurrentDate() {
        moodKeeperMock.saveCurrentMood(5)
        moodKeeperMock.saveCurrentComment("test123")
        moodKeeperMock.setCurrentDate()
        assertEquals(null, moodKeeperMock.getCurrentComment())
        assertEquals(3, moodKeeperMock.getCurrentMood())
    }

    @Test
    fun saveDay() {
        moodKeeperMock.saveCurrentMood(5)
        moodKeeperMock.saveCurrentComment("test123")
        moodKeeperMock.saveDay()
        assertEquals(5, moodKeeperMock.getMoodOn("Sunday"))
        assertEquals("test123", moodKeeperMock.getCommentOn("Sunday"))
    }
}