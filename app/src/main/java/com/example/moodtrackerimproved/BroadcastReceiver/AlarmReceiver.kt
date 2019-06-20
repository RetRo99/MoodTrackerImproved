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

        when(intent?.action){
            Intent.ACTION_BOOT_COMPLETED -> {createMidnightAlarm(context)
                                                keeper.saveDay()
                                             Log.d("Set up alarm at midnig", "huray")}
            intentAction -> {keeper.saveDay()
                            Log.d("Saved day", "huray")}
        }


    }


}