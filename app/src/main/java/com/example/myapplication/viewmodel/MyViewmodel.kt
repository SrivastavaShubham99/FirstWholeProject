package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.response.ResourceListResponse
import com.example.myapplication.data.response.ResponseUserList
import com.example.myapplication.network.ApiResponse
import com.example.myapplication.repository.UserRespository
import kotlinx.coroutines.launch

class MyViewmodel(private val repository:UserRespository):ViewModel() {
      val userLiveData=MutableLiveData<ApiResponse<ResponseUserList>>()
    val resourceLiveData = MutableLiveData<ApiResponse<ResourceListResponse>>()
    fun getUsers(){
        viewModelScope.launch {
            repository.getUsers(userLiveData)
        }
    }

    fun getResource(){
        viewModelScope.launch {
            repository.getResources(resourceLiveData)
        }
    }
}