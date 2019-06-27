package com.example.moodtrackerimproved.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.moodtrackerimproved.Logic.MoodKeeper
import com.example.moodtrackerimproved.Utils.createMidnightAlarm

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val intentAction = "MIDNIGHT_ALARM"
        val keeper = MoodKeeper(context)

        //It either receives on boot action on my custom action
        when (intent?.action) {
            //If it is a reboot it registers alarm again and check if it needs to save the day
            Intent.ACTION_BOOT_COMPLETED -> {
                createMidnightAlarm(context)
                keeper.saveDay()
                Log.d("Set up alarm at midnig", "huray")
            }
            //if it it our action then it saves the day
            intentAction -> {
                keeper.saveDay()
                Log.d("Saved day", "huray")
            }
        }


    }


}