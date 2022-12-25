package com.example.schedalp.repository

import com.example.schedalp.retrofit.EndPointApi
import okhttp3.MultipartBody
import java.util.Date
import java.util.Timer
import javax.inject.Inject

class ScheduleRepository @Inject constructor(private val api: EndPointApi){
    suspend fun getAllScheduleData() =
        api.getAllSchedule()

    suspend fun CreateSchedule(
        schedule_name: String,
        date: String,
        enddate: String,
        waktu: String,
        endwaktu: String,
        activity: String
    ) {
        val requestBody: MultipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("schedule_name", schedule_name)
            .addFormDataPart("date", date)
            .addFormDataPart("enddate", enddate)
            .addFormDataPart("waktu", waktu)
            .addFormDataPart("endwaktu", endwaktu)
            .addFormDataPart("activity", activity)
            .build()

        api.CreateSchedule(requestBody)
    }
}