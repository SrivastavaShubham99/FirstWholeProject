package com.example.myapplication.di

import com.example.myapplication.remote.RestApi

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import retrofit2.create
import okhttp3.logging.HttpLoggingInterceptor
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.remote.HttpInterceptor
import com.example.myapplication.data.remote.NetworkConnectionInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


var NetworkModule= module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY
        }
        else HttpLoggingInterceptor.Level.NONE

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(NetworkConnectionInterceptor(androidContext()))
        httpClient.addInterceptor(HttpInterceptor(androidContext()))
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }

        val okHttpClient=httpClient.build()
        Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build().create(RestApi::class.java)
    }
}