package com.example.moodtrackerimproved.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.comment_dialog.view.*

fun createDialog(context: Context, model: MainActivityViewModel) {

    //Inflating the AlertDialog view from layout we defined in comment_dialog
    val mDialogView = LayoutInflater.from(context).inflate(R.layout.comment_dialog, null)

    //Getting AlertDialog builder from Context and Inflated AlertDialog view
    val mBuilder = AlertDialog.Builder(context)
        .setView(mDialogView)

    //Checking if the current day has comment. If it has set it to edit text in AlertDialog
    model.getCurrentComment()?.let {
        mDialogView.dialogEditText.setText(it)
    }
    //Showing the AlertDialog with AlertDialog builder
    val mAlertDialog = mBuilder.show()


    //Show the keyboard when AlertDialog is shown
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)


    //Set the focus in EditText after the last letter in EditText
    mDialogView.dialogEditText.requestFocus()
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

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

