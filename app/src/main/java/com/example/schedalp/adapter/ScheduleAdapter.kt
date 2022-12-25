package com.example.schedalp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedalp.R
import com.example.schedalp.model.Data


class ScheduleAdapter(private val dataSet: ArrayList<Data>) :
        RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (Schedule ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schedulename: TextView
        val startdate: TextView
        val enddate: TextView
        val startwaktu: TextView
        val endwaktu: TextView
        val activity: TextView



        init {
            // Define click listener for the ViewHolder's View
            schedulename = view.findViewById(R.id.schedulename)
            startdate = view.findViewById(R.id.startdate)
            enddate = view.findViewById(R.id.enddate)
            startwaktu = view.findViewById(R.id.startwaktu)
            endwaktu = view.findViewById(R.id.endwaktu)
            activity = view.findViewById(R.id.activity)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.schedulecard, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.schedulename.text = dataSet[position].schedule_name
        viewHolder.startdate.text = dataSet[position].date
        viewHolder.enddate.text = dataSet[position].enddate
        viewHolder.startwaktu.text = dataSet[position].waktu
        viewHolder.endwaktu.text = dataSet[position].endwaktu
        viewHolder.activity.text = dataSet[position].activity



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
