package com.example.moodtrackerimproved.Utils

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import com.example.moodtrackerimproved.BroadcastReceiver.AlarmReceiver
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.comment_dialog.view.*
import java.util.*

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

fun createDialog(context: Context, model: MainActivityViewModel) {

    //Inflating the AlertDialog view from layout we defined in comment_dialog
    val mDialogView = LayoutInflater.from(context).inflate(R.layout.comment_dialog, null)

    //Getting AlertDialog builder from Context and Inflated AlertDialog view
    val mBuilder = AlertDialog.Builder(context)
        .setView(mDialogView)

    //Checking if the current day has comment. If it has set it to edit text in AlertDialog
    if (model.getCurrentComment() != null) mDialogView.dialogEditText.setText(model.getCurrentComment())
    //Showing the AlertDialog from AlertDialog builder
    val mAlertDialog = mBuilder.show()


    //Show the keyboard when AlertDialog is shown
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

