package com.example.myapplication.data.remote

import android.content.Context
import android.os.Looper.loop
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.util.concurrent.Semaphore
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.jvm.Throws


internal class HttpInterceptor(var androidContext: Context) : Interceptor, KoinComponent {

    @Throws(IOException::class,SocketTimeoutException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        request = builder.build() //overwrite old request
        var response = chain.proceed(request) //perform request, here original request will be executed
        return response
    }

}