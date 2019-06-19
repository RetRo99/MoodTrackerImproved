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
import com.example.moodtrackerimproved.Mood.Mood
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.Utils.Utils
import com.example.moodtrackerimproved.ViewModel.MainActivityViewModel
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

        if(isFirstTime()) setAlarmManager()

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

    @SuppressLint("InflateParams")
    private fun createDialog() {

        //Inflating the AlertDialog view from layout we defined in comment_dialog
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.comment_dialog, null)

        //Getting AlertDialog builder from Context and Inflated AlertDialog view
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)

        //Checking if the current day has comment. If it has set it to edit text in AlertDialog
        if (model.getCurrentComment() != null) mDialogView.dialogEditText.setText(model.getCurrentComment())
        //Showing the AlertDialog from AlertDialog builder
        val mAlertDialog = mBuilder.show()


        //Show the keyboard when AlertDialog is shown
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)


        //Set the focus in EditText after the last letter in EditText
        mDialogView.dialogEditText.setSelection(mDialogView.dialogEditText.text.length)

        //Set a listener on Cancel Button
        mDialogView.cancelButton.setOnClickListener {
            //Dismiss the AlertDialog
            mAlertDialog.dismiss()
        }
        //Set a listener on Confirm Button
        mDialogView.confirmButton.setOnClickListener {
            //Saving the text in EditText
            model.setCurrentComment(mDialogView.dialogEditText.text.toString())
            //Dismiss the AlertDialog
            mAlertDialog.dismiss()
        }

        //Setting a listener on when the AlertDialog is closed
        mAlertDialog.setOnDismissListener {
            //Hide the keyboard
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

        }


    }

    //Set up the screen based on parameters
    private fun setScreen(mood: Mood) {
        layout_main_activity.setBackgroundResource(mood.backgroundResource)//Set the background of the layout
        face_image_view.setBackgroundResource(mood.photoResource) //Set up the Smiley

        //Setting the MediaPlayer
        val mediaPath: Uri = Uri.parse("android.resource://$packageName/${mood.soundResource}")


        //Setting up the datasource for MediaPlayer
        try {

            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(this, mediaPath)
            mediaPlayer?.prepare()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        currentMood = mood

    }

    private fun setOnFaceImageClick(){
        face_image_view.setOnClickListener {
            mediaPlayer?.start()
            model.setCurrentMood(currentMood.mood)
        }

    }

    private fun setOnHistoryImageClick(){
        imageViewHistory.setOnClickListener {
            //Creating an intent that we will use to start new Activity
            val intent = Intent(this, HistoryActivity::class.java)
            //Starting the activity with intent we created
            startActivity(intent)
        }

    }
    private fun setOnCommentImageClick(){
        imageViewComment.setOnClickListener {
            createDialog()
        }


    }

    private fun isFirstTime(): Boolean{
        //For checking if this is the first time app has been run
        val firstTime = "First_time"
        val settings = getSharedPreferences(firstTime, 0)
        return settings.getBoolean("my_first_time", true)




    }

    private fun setAlarmManager () {
        //the app is being launched for first time, do something
        Log.d("Comments", "First time")
        val utils = Utils()
        utils.createMidnightAlarm(this)
        model.setCurrentDate()

    }




}




