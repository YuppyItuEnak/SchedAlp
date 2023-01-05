package com.example.schedalp.view

import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.format.DateFormat
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.schedalp.*
import com.example.schedalp.adapter.ScheduleAdapter
import com.example.schedalp.adapter.UserAdapter
import com.example.schedalp.databinding.ActivityAddScheduleBinding
import com.example.schedalp.model.Data
import com.example.schedalp.viewmodel.ScheduleViewModel
import com.example.schedalp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class AddScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddScheduleBinding

    //    private lateinit var adapter: ScheduleAdapter
    private lateinit var schedule: ScheduleViewModel
//    private lateinit var datalist: ArrayList<Data>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        supportActionBar!!.title = "Add Schedule"
        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupListener() {
        createNotificationChannel()
        binding.submitformadd.setOnClickListener {
            ScheduleNotification()
//            val schedule_name = binding.inputschedule.editText?.text.toString()
//            val date = binding.inputstartdate.editText?.text.toString()
//            val enddate = binding.inputenddate.editText?.text.toString()
//            val waktu = binding.inputstartwaktu.editText?.text.toString()
//            val endwaktu = binding.inputendwaktu.editText?.text.toString()
//            val activity = binding.inputactivity.editText?.text.toString()


//            if (schedule_name.isEmpty() || date.isEmpty() || enddate.isEmpty() || waktu.isEmpty() || endwaktu.isEmpty() || activity.isEmpty()){
//                Toast.makeText(this, "Please Enter required field", Toast.LENGTH_LONG).show()
//            }else{
//                schedule = ViewModelProvider(this)[ScheduleViewModel::class.java]
//
//                schedule.state = schedule.state.copy(
//                    schedule_name = schedule_name,
//                    date = date,
//                    enddate = enddate,
//                    waktu = waktu,
//                    endwaktu = endwaktu,
//                    activity = activity
//                )
//               schedule.createSchedule()
//               val intent = Intent(this, MainActivity::class.java)
//               startActivity(intent)
//               finish()
//            }
//
//        }
        }
    }

    private fun ScheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val schedule_name = binding.inputschedule.editText!!.text.toString()
        val activity = binding.inputactivity.editText?.text.toString()
        intent.putExtra(titleExtra, schedule_name)
        intent.putExtra(messageExtra, activity)

        val pendingintent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmmanager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmmanager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingintent,
        )

        schedule = ViewModelProvider(this)[ScheduleViewModel::class.java]

        val date = Date(time)
        val dformat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dformat.format(date)
        val dtime = SimpleDateFormat("HH:mm:ss")
        val formattedTime = dtime.format(time)

        schedule.state = schedule.state.copy(
            schedule_name = schedule_name,
            date = formattedDate,
            waktu = formattedTime,
            activity = activity
        )
        schedule.createSchedule()
//        if(){
//            schedule.UpdateSchedule()
//        }

        showAlert(time, schedule_name, activity)
    }

    private fun showAlert(time: Long, scheduleName: String, activity: String) {
        val date = Date(time)
        val dformat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dformat.format(date)
        val dtime = SimpleDateFormat("HH:mm:ss")
        val formattedTime = dtime.format(time)

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Schedule Name: " + scheduleName +
                        "\nActivity: " + activity + "\nAt: " + formattedDate +
                        " " + formattedTime
            )
            .setPositiveButton("Okay") { _, _ ->
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }
            .show()
    }

    private fun getTime(): Long {
        val minute = binding.TimePicker.minute
        val hour = binding.TimePicker.hour
        val day = binding.DatePicker.dayOfMonth
        val month = binding.DatePicker.month
        val year = binding.DatePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val schedule_name = "Notif Channel"
        val activity = "A Description for notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, schedule_name, importance)
        channel.description = activity
        val notificationmanager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationmanager.createNotificationChannel(channel)
    }


//    private fun UpdateSchedule(){
//        binding.submitformadd.setOnClickListener {
//        var id = 0
//        if(intent.hasExtra("updateschedule")){
//            id = intent.getIntExtra("updateschedule", 0)
//
//        }
//        if (id != 0){
//            schedule = ViewModelProvider(this)[ScheduleViewModel::class.java]
//
//
//            schedule.dataschedule.observe(this, { response ->
//                var schedule_name = response.
//                var date =
//                var waktu =
//                var activity =
//            } )
//
//            schedule.state = schedule.state.copy(
//                schedule_name =
//                date =
//
//                waktu =
//
//                activity =
//            )
//            schedule
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//
//            }
//        }
//    }


    //     untuk back button kembali ke home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}