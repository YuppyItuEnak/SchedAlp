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
    @GET("getschedule")
    suspend fun getSchedule(): Response<ScheduleData>

    @PATCH("updateschedule")
    suspend fun updateschedule(): Response<ScheduleData>

    @POST("createschedule")
    suspend fun createSchedule(@Body body: RequestBody?): ResponseBody?



    @GET("profile/{userid}")
    suspend fun getUser(
        @Path("id") id:Int
    ): Response<UserData>

    @POST("/login")
   fun Login(@Field("username") username: String, @Field("password") password: String): Call<Login>

    @POST("createuser")
    suspend fun createuser(@Body body: RequestBody?): ResponseBody?
//    suspend fun CreateSchedule(): Response<ScheduleData>
}