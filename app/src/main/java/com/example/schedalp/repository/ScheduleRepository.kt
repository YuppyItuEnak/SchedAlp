package com.example.schedalp.repository

import com.example.schedalp.model.Data
import com.example.schedalp.model.ScheduleData
import com.example.schedalp.model.ScheduleState
import com.example.schedalp.retrofit.EndPointApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class ScheduleRepository @Inject constructor(private val api: EndPointApi){
    suspend fun getAllScheduleData() =
        api.getAllSchedule()

    suspend fun getSchedule(schdlid: Int) = api.getSchedule(schdlid)

    suspend fun DeleteSchedule(id: Int) = api.deleteschedule(id)
//    suspend fun updateschedule()

    suspend fun UpdateSchedule(
        id:String,
        schedule_name: String,
        date: String,
        waktu: String,
        activity: String,
    ){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id", id)
            .addFormDataPart("schedule_name", schedule_name)
            .addFormDataPart("date", date)
            .addFormDataPart("waktu", waktu)
            .addFormDataPart("activity", activity)
            .build()

        api.updateschedule(requestBody)
    }

    suspend fun createSchedule(

        schedule_name: String,
        date: String,
        waktu: String,
        activity: String,
    ){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

            .addFormDataPart("schedule_name", schedule_name)
            .addFormDataPart("date", date)
            .addFormDataPart("waktu", waktu)
            .addFormDataPart("activity", activity)
            .build()

        api.createSchedule(requestBody)
    }



}