package com.example.moodtrackerimproved.Activites

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.ViewModelProviders
import com.example.moodtrackerimproved.Gestures.GestureListener
import com.example.moodtrackerimproved.Model.Mood
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.Utils.createDialog
import com.example.moodtrackerimproved.Utils.createMidnightAlarm
import com.example.moodtrackerimproved.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.comment_dialog.view.*


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

        //Creating MediaPlayer
        mediaPlayer = MediaPlayer()

        //Setting up ViewModel with ViewModelProviders to return the same instance if I need it elsewhere
        model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        //Setting up gesture listener -> passing in ViewModel, could pass in Context and create ViewModel there
        val gestureListener = GestureListener(model)

        //Create gesture detector  with Context and Gesture Listener
        mGestureDetector = GestureDetectorCompat(this, gestureListener)

        if (isFirstTime()) setAlarmManager()

        setOnCommentImageClick()
        setOnFaceImageClick()
        setOnHistoryImageClick()

        val moodOnScreenObserver: androidx.lifecycle.Observer<Mood> =
            androidx.lifecycle.Observer { moodOnScreen -> setScreen(moodOnScreen) }

        model.moodOnScreen.observe(this, moodOnScreenObserver)

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

    private fun setOnFaceImageClick() {
        face_image_view.setOnClickListener {
            mediaPlayer?.start()
            model.setCurrentMood(currentMood.moodInt)
        }

    }

    private fun setOnHistoryImageClick() {
        imageViewHistory.setOnClickListener {
            //Creating an intent that we will use to start new Activity
            val intent = Intent(this, HistoryActivity::class.java)
            //Starting the activity with intent we created
            startActivity(intent)
        }

    }

    private fun setOnCommentImageClick() {
        imageViewComment.setOnClickListener {
            createDialog(this, model)
        }


    }

    private fun isFirstTime(): Boolean {
        //For checking if this is the first time app has been run
        val firstTime = "First_time"
        val settings = getSharedPreferences(firstTime, 0)
        return settings.getBoolean("my_first_time", true)


    }


    private fun setAlarmManager() {
        //the app is being launched for first time, do something
        Log.d("Comments", "First time")
        createMidnightAlarm(this)
        model.setCurrentDate()

    }

}




