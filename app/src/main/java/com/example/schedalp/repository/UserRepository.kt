package com.example.schedalp.repository

import com.example.schedalp.retrofit.EndPointApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: EndPointApi){

    suspend fun getUser(id:Int) = api.getUser(id)

    fun LoginUser(
        username: String,
        password: String,
    ) = api.Login(username, password)


    suspend fun Register(
        email: String,
        username: String,
        password: String,
    ){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("username", username)
            .addFormDataPart("password", password)

            .build()

        api.createuser(requestBody)

    }

}