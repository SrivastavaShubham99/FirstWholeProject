package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.response.ResourceListResponse
import com.example.myapplication.data.response.ResponseUserList
import com.example.myapplication.network.ApiResponse
import com.example.myapplication.network.DataFetchCall
import com.example.myapplication.remote.RestApi
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class UserRespository() :KoinComponent{
    val mRestApi by inject<RestApi> ()
    suspend fun getUsers(liveData: MutableLiveData<ApiResponse<ResponseUserList>>){
        object : DataFetchCall<ResponseUserList>(liveData){
            override suspend fun dataCallAsync(): Response<ResponseUserList> {
                return mRestApi.getUsers()
            }
        }.execute()
    }

    suspend fun getResources(resourceLiveData : MutableLiveData<ApiResponse<ResourceListResponse>>) {
        object : DataFetchCall<ResourceListResponse>(resourceLiveData){
            override suspend fun dataCallAsync(): Response<ResourceListResponse> {
                return mRestApi.getResource()
            }
        }.execute()
    }
}