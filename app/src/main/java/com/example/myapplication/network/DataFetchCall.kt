package com.example.myapplication.network

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.remote.RestApi
import kotlinx.coroutines.CancellationException
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.lang.Exception

abstract  class DataFetchCall<T>(private val liveData: MutableLiveData<ApiResponse<T>>) :KoinComponent{
    val restApi by inject<RestApi>()
    abstract suspend fun dataCallAsync():Response<T>
    suspend fun execute(){
        try {
            val response=dataCallAsync()
            if(response.body()!=null && response.code()==200){
                liveData.postValue(ApiResponse.loading())
                liveData.postValue(ApiResponse.success(response.body()!!))
            }
            else{
                if(response !=null){
                    liveData.postValue(ApiResponse.error(
                        ApiResponse.Error(
                            response.code(),
                            response.message(),
                            response.errorBody().toString()
                        )
                    ))
                }
                else{
                    liveData.postValue(ApiResponse.error(
                        ApiResponse.Error(
                            500,
                            "galat kiya ",
                            "bhut galat kiya"
                        )
                    ))
                }
            }
        }catch (e:Exception){
            if (e !is CancellationException) {
                println(e.message)
              liveData.postValue(ApiResponse.error(
                  ApiResponse.Error(
                      code = 500,
                      message = "Server not Reachable",
                      errorBody = "error"
                  )
              )
              )
            }
        }
    }
}