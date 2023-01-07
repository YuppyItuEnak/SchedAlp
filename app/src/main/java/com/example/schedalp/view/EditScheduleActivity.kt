package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.schedalp.R
import com.example.schedalp.adapter.ScheduleAdapter
import com.example.schedalp.databinding.ActivityEditScheduleBinding
import com.example.schedalp.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EditScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditScheduleBinding
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var ScheduleVM: ScheduleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EditSchedule()

    }

    private fun EditSchedule(){
        binding.editsubmit.setOnClickListener {
            ScheduleVM = ViewModelProvider(this@EditScheduleActivity)[ScheduleViewModel::class.java]

            val editTime = getTime()
            val edidate = Date(editTime)
            val formatdate = SimpleDateFormat("yyyy-MM-dd")
            val editedformatd = formatdate.format(edidate)

            val formattime = SimpleDateFormat("HH:mm:ss")
            val editedFormatTime = formattime.format(editTime)
            val editschdname = binding.editschdname.editText?.text.toString()
            val editactivity = binding.editactivity.editText?.text.toString()
            ScheduleVM.state = ScheduleVM.state.copy(
                id = intent.getIntExtra("shcd_id", 0).toString(),
                schedule_name = editschdname,
                date = editedformatd,
                waktu = editedFormatTime,
                activity = editactivity
            )
            ScheduleVM.UpdateSchedule()
            val editdone = Intent(applicationContext, MainActivity::class.java)
            startActivity(editdone)
        }

    }

    private fun getTime(): Long {
        val minute = binding.edittime.minute
        val hour = binding.edittime.hour
        val day = binding.editdate.dayOfMonth
        val month = binding.editdate.month
        val year = binding.editdate.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }
}