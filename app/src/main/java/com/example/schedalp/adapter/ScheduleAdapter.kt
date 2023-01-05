package com.example.schedalp.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.schedalp.R
import com.example.schedalp.model.Data
import com.example.schedalp.retrofit.Listener
import com.example.schedalp.view.AddScheduleActivity
import com.example.schedalp.view.MainActivity
import com.example.schedalp.viewmodel.ScheduleViewModel


class ScheduleAdapter(private val dataSet: ArrayList<Data>,
) :
        RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (Schedule ViewHolder)
     */
    private lateinit var schedule: ScheduleViewModel

    private lateinit var onEditClickListener: OnEditClickListener


    interface OnEditClickListener {
        fun onEditClick(position: Int)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schedulename: TextView
        val startdate: TextView
        val startwaktu: TextView
        val activity: TextView
        val edit: Button
        val delete: Button
//        val mainrv = RecyclerView







        init {
            // Define click listener for the ViewHolder's View
            schedulename = view.findViewById(R.id.schedulename)
            startdate = view.findViewById(R.id.startdate)
            startwaktu = view.findViewById(R.id.startwaktu)

            activity = view.findViewById(R.id.activity)
            edit = view.findViewById(R.id.edit)
            delete = view.findViewById(R.id.delete)
//            mainrv = view.findViewById(R.id.mainrv)


//            //Utk Inputan User
//            inputschedule = view.findViewById(R.id.inputschedule)
//            inputstartdate = view.findViewById(R.id.startdate)
//            inputenddate = view.findViewById(R.id.enddate)
//            inputstartwktu = view.findViewById(R.id.startwaktu)
//            inputendwaktu = view.findViewById(R.id.endwaktu)
//            inputactivity = view.findViewById(R.id.inputactivity)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.schedulecard, viewGroup, false)

        return ViewHolder(view)
    }

    fun setOnEditClickListener(onEditClickListener: OnEditClickListener) {
        this.onEditClickListener = onEditClickListener
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
            val intent = Intent(it.context, AddScheduleActivity::class.java)
            intent.putExtra("updateSchedule_id", dataSet[position].id)
            it.context.startActivity(intent)
        }
        viewHolder.delete.setOnClickListener {

//            schedule = ViewModelProvider()[ScheduleViewModel::class.java]

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
