package com.example.moodtrackerimproved.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.moodtrackerimproved.SharedPreferences.MoodKeeper
import com.example.moodtrackerimproved.Utils.createMidnightAlarm

@Suppress("NAME_SHADOWING")
class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val intentAction = "MIDNIGHT_ALARM"

        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            createMidnightAlarm(context)
            Log.d("bradcast alaram run", "huray")

        }

        if (intent?.action == intentAction) {
            val keeper = MoodKeeper(context)
            keeper.saveDay()
            Log.d("bradcast new day run", "huray")
        }

    }


}