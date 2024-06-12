package com.example.suitmediatest.data.retrofit

import com.example.suitmediatest.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=1&per_page=10")
    suspend fun getUsers(

    ):UserResponse
}