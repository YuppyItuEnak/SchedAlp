package com.example.schedalp.retrofit

import com.example.schedalp.model.ScheduleData
import com.example.schedalp.model.ScheduleState
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPointApi {

    @GET("schedule")
    suspend fun getAllSchedule(): Response<ScheduleData>

    @FormUrlEncoded
    @POST
    suspend fun CreateSchedule(@Body requestBody: MultipartBody): ResponseBody


//    suspend fun CreateSchedule(): Response<ScheduleData>
}