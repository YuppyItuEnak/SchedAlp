package com.example.schedalp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedalp.R
import com.example.schedalp.model.DataX

class UserAdapter (private val userdataset: ArrayList<DataX>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val username: TextView
    val email: TextView

        init {
            username = view.findViewById(R.id.username)
            email = view.findViewById(R.id.username)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.navheader, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = userdataset[position].username
        holder.email.text = userdataset[position].email
    }

    override fun getItemCount() = userdataset.size
}
