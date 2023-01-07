package com.example.schedalp.retrofit

import com.example.schedalp.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EndPointApi {

    @GET("schedule")
    suspend fun getAllSchedule(): Response<ScheduleData>

    @GET("/getschedule/{schdlid}")
    suspend fun getSchedule(@Path("schdlid") schdlid: Int): Response<Data>

    @PATCH("updateschedule")
    suspend fun updateschedule(@Body body: RequestBody?): ResponseBody?

    @POST("createschedule")
    suspend fun createSchedule(@Body body: RequestBody?): ResponseBody?

    @DELETE("deleteschedule/{id}")
    suspend fun deleteschedule(@Field("id") id: Int): Response<Delete>



    @GET("profile/{userid}")
    suspend fun getUser(
        @Path("id") id:Int
    ): Response<UserData>


    @POST("loginuser")
   suspend fun login(@Body body: RequestBody?): ResponseBody?

    @POST("createuser")
    suspend fun createuser(@Body body: RequestBody?): ResponseBody?
//    suspend fun CreateSchedule(): Response<ScheduleData>
}