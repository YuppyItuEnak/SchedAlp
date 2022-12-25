package com.example.schedalp.model


    data class ScheduleState(
        val schedule_name: String = "",
        val date: String = "",
        val enddate: String = "",
        val waktu: String = "",
        val endwaktu: String = "",
        val activity: String = "",
        val isLoading: Boolean = false,
    )
