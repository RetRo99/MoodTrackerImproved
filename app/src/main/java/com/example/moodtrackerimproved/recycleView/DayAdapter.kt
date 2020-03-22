package com.example.moodtrackerimproved.recycleView

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerimproved.R
import com.example.moodtrackerimproved.model.Day
import com.example.moodtrackerimproved.utils.inflate
import kotlinx.android.synthetic.main.row_item.view.*

class DayAdapter(private val height: Int, private val width: Int) :
    RecyclerView.Adapter<DayAdapter.DayHolder>() {

    lateinit var daysInOrder: List<Day>

    //class with viewHolders that holds the references to layout items
    class DayHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindDay(day: Day, height: Int, width: Int) {

            val params = view.constraintLayoutRecycle.layoutParams
            params.width = width / day.mood.moodInt
            params.height = height + 1 // adding one because of rounding up errors, without this it leaves empty space in layout
            //Setting the height of the item
            view.constraintLayoutRecycle.layoutParams = params

            //Check if day has comment
            if (!day.hasComment) {
                //If it does not have a comment hide the ImageView (it is shown by default as set in history_activity.xml)
                view.commentImage.visibility = View.INVISIBLE
            } else {
                //If it has a comment create a an onClickListener that displays toast using Day objects comment
                view.constraintLayoutRecycle.setOnClickListener {
                    Toast.makeText(view.context, day.comment, Toast.LENGTH_LONG).show()
                }


            }
            //Setting the color based on the mood
            view.constraintLayoutRecycle.setBackgroundResource(day.mood.backgroundResource)

            //Setting the text in textview so we how many days ago this mood happened
            view.daysAgoTextView.text = day.daysAgo

        }

    }


    //Inflating the Dayholder from the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val inflatedView = parent.inflate(R.layout.row_item, false)
        return DayHolder(inflatedView)
    }

    //Getting the item count
    override fun getItemCount() = daysInOrder.size


    //Invoking the viewsholder function to bind data to the viewholder
    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        val day = daysInOrder[position]
        holder.bindDay(day, height, width)
    }


}
