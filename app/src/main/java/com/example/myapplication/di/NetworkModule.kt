package com.example.myapplication.di

import com.example.myapplication.remote.RestApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


var NetworkModule= module {
    single {
        val httpClient=OkHttpClient.Builder()
        val okHttpClient=httpClient.build()
        Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build().create(RestApi::class.java)
    }
}