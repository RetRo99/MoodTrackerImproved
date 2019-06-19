package com.example.moodtrackerimproved

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.example.moodtrackerimproved.SharedPreferences.MoodKeeper
import com.example.moodtrackerimproved.Utils.Utils
import java.util.*

@Suppress("NAME_SHADOWING")
class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val intentAction = "MIDNIGHT_ALARM"

        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val utils = Utils()
            utils.createMidnightAlarm(context)
            Log.d("bradcast alaram run", "huray")

        }

        if (intent?.action == intentAction) {
            val keeper = MoodKeeper(context)
            keeper.saveDay()
            Log.d("bradcast new day run", "huray")
        }

    }


}