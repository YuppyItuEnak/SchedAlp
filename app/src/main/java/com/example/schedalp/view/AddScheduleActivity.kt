package com.example.schedalp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.schedalp.R
import com.example.schedalp.databinding.ActivityAddScheduleBinding
import com.example.schedalp.repository.ScheduleRepository
import com.example.schedalp.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class AddScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddScheduleBinding
    private lateinit var repository: ScheduleRepository
    private lateinit var viewModel: ScheduleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        supportActionBar!!.title = "Add Schedule"
        if(supportActionBar != null){
        supportActionBar!!.setHomeButtonEnabled(true)
        }
    }

    

    private fun setupListener(){
        binding.submitformadd.setOnClickListener {
            val schedulename = binding.inputschedule.editText.toString()
            val startdate = binding.inputstartdate.editText.toString()
            val enddate = binding.inputenddate.editText.toString()
            val startwaktu = binding.inputstartwaktu.editText.toString()
            val endwaktu = binding.inputendwaktu.editText.toString()
            val activity = binding.inputactivity.editText.toString()

            if (schedulename.isEmpty() || startdate.isEmpty() || enddate.isEmpty() || startwaktu.isEmpty() || endwaktu.isEmpty() || activity.isEmpty()){
                Toast.makeText(this, "Please Enter required field", Toast.LENGTH_LONG).show()
            }
        }
    }


    // untuk back button kembali ke home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}