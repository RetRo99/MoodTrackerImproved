package com.example.moodtrackerimproved.gestures

import android.view.GestureDetector
import android.view.MotionEvent
import com.example.moodtrackerimproved.viewModel.MainActivityViewModel
import kotlin.math.abs

//Creating a class that extends  GestureDetector.OnGestureListener which detects clicks on screen. We take  model in
//constructor. We only override the onFling function
class GestureListener(private val model: MainActivityViewModel) :
    GestureDetector.OnGestureListener {

    //Setting up the minimum swipe distance
    private val swipeThreshold = 50

    //Setting up minimal swipe velocity
    private val swipeVelocityThreshold = 50

    //For this we only need the onFling method no need to implement other methods
    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            val diffY = e2.x - e1.x
            val diffX = e2.y - e1.y

            //Checking if the swipe was UP or DOWN
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {


                    if (diffX > 0) {//Swipe down
                        model.increment()
                    } else { //Swipe up
                        model.decrement()
                    }
                }
            }


        } catch (exception: Exception) {
            exception.printStackTrace()
        }


        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }


    override fun onLongPress(e: MotionEvent?) {
    }
}






