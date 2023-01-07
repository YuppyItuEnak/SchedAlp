package com.example.schedalp.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedalp.R
import com.example.schedalp.model.Data
import com.example.schedalp.view.EditScheduleActivity
import com.example.schedalp.viewmodel.ScheduleViewModel


class ScheduleAdapter(private val dataSet: ArrayList<Data>,
) :
        RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (Schedule ViewHolder)
     */
    private lateinit var schedule: ScheduleViewModel






    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schedulename: TextView
        val startdate: TextView
        val startwaktu: TextView
        val activity: TextView
        val edit: CardView
        val delete: CardView

//        val mainrv = RecyclerView

        init {
            // Define click listener for the ViewHolder's View
            schedulename = view.findViewById(R.id.schedulename)
            startdate = view.findViewById(R.id.startdate)
            startwaktu = view.findViewById(R.id.startwaktu)
            activity = view.findViewById(R.id.activity)
            edit = view.findViewById(R.id.edit)
            delete = view.findViewById(R.id.delete)
//

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
        viewHolder.startwaktu.text = dataSet[position].waktu
        viewHolder.activity.text = dataSet[position].activity

        viewHolder.edit.setOnClickListener {
            val editsched = Intent(it.context, EditScheduleActivity::class.java)
            editsched.putExtra("shcd_id", dataSet[position].id)
            it.context.startActivity(editsched)
        }
        viewHolder.delete.setOnClickListener {

        }



//        viewHolder.edit.setOnClickListener {
//           val id = dataSet[position].id
//
////
//            viewHolder.schedulename.text = dataSet[id!!].schedule_name
//            viewHolder.startdate.text = dataSet[id].date
//            viewHolder.startwaktu.text = dataSet[id].waktu
//            viewHolder.activity.text = dataSet[id].activity
//
//
//
////            onEditClickListener.onEditClick(position)
//        }









    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size



}
