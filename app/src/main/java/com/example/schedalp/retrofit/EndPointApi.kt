package com.example.schedalp.retrofit

import com.example.schedalp.model.Data
import com.example.schedalp.model.ScheduleData
import com.example.schedalp.model.ScheduleState
import com.example.schedalp.model.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface EndPointApi {

    @GET("schedule")
    suspend fun getAllSchedule(): Response<ScheduleData>
    @GET("getschedule")
    suspend fun getSchedule(): Response<ScheduleData>

    @PATCH("updateschedule")
    suspend fun updateschedule(): Response<ScheduleData>

    @POST("createschedule")
    suspend fun createSchedule(@Body body: RequestBody?): ResponseBody?



    @GET("user")
    suspend fun getAllUser(): Response<UserData>
    @POST("createuser")
    suspend fun createuser(@Body body: RequestBody?): ResponseBody?
//    suspend fun CreateSchedule(): Response<ScheduleData>
}