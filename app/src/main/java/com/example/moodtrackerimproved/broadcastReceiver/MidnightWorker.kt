package com.example.moodtrackerimproved.broadcastReceiver

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.moodtrackerimproved.logic.MoodKeeper

class MidnightWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    private val keeper = MoodKeeper(appContext)

    override fun doWork(): Result {
        keeper.saveDay()
        return Result.success()

    }


}
