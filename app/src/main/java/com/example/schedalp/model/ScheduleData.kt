package com.example.schedalp.model

data class ScheduleData(
    val `data`: List<Data>,
    val message: String,
    val status: Int
)