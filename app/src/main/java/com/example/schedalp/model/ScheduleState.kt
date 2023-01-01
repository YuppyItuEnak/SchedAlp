package com.example.schedalp.model

import android.text.format.DateFormat
import java.sql.Time
import java.time.Year
import java.util.*


data class ScheduleState(
    val schedule_name: String = "",
    val date: Long = 0  ,

    val waktu: Long = 0,

    val activity: String = "",

    )
