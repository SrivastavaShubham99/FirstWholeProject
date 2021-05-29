package com.example.myapplication.remote

import com.example.myapplication.data.response.ResourceListResponse
import com.example.myapplication.data.response.ResponseUserList
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {
    @GET("/api/users?page=2")
    suspend fun getUsers():Response<ResponseUserList>

    @GET("/api/unknown")
    suspend fun getResource():Response<ResourceListResponse>
}