package com.example.moodtrackerimproved.Utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.moodtrackerimproved.AlarmReceiver
import java.util.*

class Utils() {


    fun createMidnightAlarm(context: Context) {

        val intentAction = "MIDNIGHT_ALARM"


        //Setting up alarm every day on midnight


        //Creating new intent object
        val intent = Intent(context, AlarmReceiver::class.java)

        //Adding action to the intent
        intent.action = intentAction

        //Creating PendingIntent from previously created intent
        val pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        //Getting the calendar instance and setting it to next midnight
        val setCalendar = Calendar.getInstance()
        setCalendar.set(Calendar.HOUR_OF_DAY, 0)
        setCalendar.set(Calendar.MINUTE, 0)
        setCalendar.set(Calendar.SECOND, 0)
        setCalendar.add(Calendar.DATE, 1)


        //Setting the repeating alarmmanager with real time  (RTC), passing the calendar instance as the triggerat parameter, and running it everyday
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC,
            setCalendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pi
        )

    }

    fun createDialog(){}
}