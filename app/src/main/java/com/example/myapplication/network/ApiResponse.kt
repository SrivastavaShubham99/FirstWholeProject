package com.example.myapplication.network

class ApiResponse<T>(val status:Status,val data:T?=null,val error:Error ? =null) {
    companion object{
        fun <T> loading ():ApiResponse<T>{
            return ApiResponse(Status.LOADING)
        }
        fun <T> success(data:T): ApiResponse<T>? {
            return  ApiResponse(Status.SUCCESS,data)
        }
        fun <T> error(error:Error?=null):ApiResponse<T>{
            return ApiResponse(Status.ERROR,error=error)
        }
    }
    enum class Status{
        LOADING,
        SUCCESS,
        ERROR
    }

    class Error(val code:Int,val message:String,val errorBody:String="")
}