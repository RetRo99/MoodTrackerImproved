package com.example.moodtrackerimproved.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.broadcastReceiver.MidnightWorker
import com.example.moodtrackerimproved.gestures.GestureListener
import com.example.moodtrackerimproved.model.Mood
import com.example.moodtrackerimproved.utils.createDialog
import com.example.moodtrackerimproved.viewModel.MainActivityViewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDateTime
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    //Setting up variables used in activity


    //Variable for ViewModel
    private lateinit var model: MainActivityViewModel
    private lateinit var mGestureDetector: GestureDetectorCompat
    //Variable for for MediaPlayer
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var currentMood: Mood

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //starting time library
        AndroidThreeTen.init(this)

        //Creating MediaPlayer
        mediaPlayer = MediaPlayer()

        //Setting up ViewModel with ViewModelProviders to return the same instance if I need it elsewhere
        model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        //Setting up gesture listener -> passing in ViewModel, could pass in Context and create ViewModel there
        val gestureListener = GestureListener(model)

        //Create gesture detector  with Context and Gesture Listener
        mGestureDetector = GestureDetectorCompat(this, gestureListener)

        //only set up alarm on the first run of the app
        if (isFirstTime()) setupWorkManager()

        //setting up click listeners on our images
        setOnCommentImageClick()
        setOnFaceImageClick()
        setOnHistoryImageClick()

        //registering the observer on a proprety of viewmodel
        model.moodOnScreen.observe(
            this,
            Observer { moodOnScreen -> setScreen(moodOnScreen) }
        )

    }

    //Sending the touch event to gesture detector
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }


    //Set up the screen based on parameters
    private fun setScreen(mood: Mood) {
        layout_main_activity.setBackgroundResource(mood.backgroundResource)//Set the background of the layout
        face_image_view.setBackgroundResource(mood.photoResource) //Set up the Smiley

        //Setting the MediaPlayer
        val mediaPath: Uri = Uri.parse("android.resource://$packageName/${mood.soundResource}")

        //Setting up the DataSource for MediaPlayer
        try {
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(this, mediaPath)
            mediaPlayer?.prepare()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        currentMood = mood
    }

    //set up on click listener on face image
    private fun setOnFaceImageClick() {
        face_image_view.setOnClickListener {
            mediaPlayer?.start()
            model.setCurrentMood(currentMood.moodInt)
        }

    }

    //set up on click listener on history image
    private fun setOnHistoryImageClick() {
        imageViewHistory.setOnClickListener {
            //Creating an intent that we will use to start new Activity
            val intent = Intent(this, HistoryActivity::class.java)
            //Starting the activity with intent we created
            startActivity(intent)
        }

    }

    //set up on click listener on comment image
    private fun setOnCommentImageClick() {
        imageViewComment.setOnClickListener {
            createDialog(this, model)
        }
    }

    //checking if the app is being run for the first time
    private fun isFirstTime(): Boolean {
        //For checking if this is the first time a  pp has been run
        val firstTimeSharedPref = getSharedPreferences(IS_FIRST_TIME, Context.MODE_PRIVATE)
        val isFirstTime = firstTimeSharedPref.getBoolean(IS_FIRST_TIME, true)
        if (isFirstTime) {
            firstTimeSharedPref.edit().putBoolean(IS_FIRST_TIME, false).apply()
        }
        return isFirstTime
    }

    //Setting up the alarammanager
    private fun setupWorkManager() {
        //the app is being launched for first time, do something

        //calculate time until first midnight and set a work manager
        val ldt = LocalDateTime.now()
        val secondsInDay = 86400L
        val secondsUntilMidnight = secondsInDay - ldt.toLocalTime().toSecondOfDay()
        val work = PeriodicWorkRequestBuilder<MidnightWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(secondsUntilMidnight, TimeUnit.SECONDS)
            .build()


        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            MIDNIGHT_WORKER,
            ExistingPeriodicWorkPolicy.KEEP, work
        )
        model.setCurrentDate()
    }

    companion object{
        const val MIDNIGHT_WORKER = "com.example.moodtrackerimproved.activities.midnightWorker"
        const val IS_FIRST_TIME = "com.example.moodtrackerimproved.activities.isFirstTIme"
    }

}




