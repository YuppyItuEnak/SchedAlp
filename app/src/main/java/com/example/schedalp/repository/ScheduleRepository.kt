package com.example.schedalp.repository

import com.example.schedalp.retrofit.EndPointApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class ScheduleRepository @Inject constructor(private val api: EndPointApi){
    suspend fun getAllScheduleData() =
        api.getAllSchedule()

    suspend fun getSchedule() =
        api.getSchedule()

//    suspend fun updateschedule()



    suspend fun createSchedule(
        schedule_name: String,
        date: Long,
        waktu: Long,
        activity: String,
    ){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("schedule_name", schedule_name)
            .addFormDataPart("date", date.toString())
            .addFormDataPart("waktu", waktu.toString())
            .addFormDataPart("activity", activity)
            .build()

        api.createSchedule(requestBody)
    }

}