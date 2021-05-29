package com.example.myapplication.data.remote

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws


class NetworkConnectionInterceptor constructor(var context: Context) : Interceptor {
    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isOnline(context,false)) {
            throw NoInternetException(message ="no internet")
        }
        return chain.proceed(chain.request())
    }

}

class NoInternetException(message: String) : IOException(message)

object NetworkUtils {

    fun isOnline(context: Context, showToast: Boolean = true): Boolean {
        // Checking internet connectivity
        val connectivityMgr =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var activeNetwork: NetworkInfo? = null
        if (connectivityMgr != null) {
            activeNetwork = connectivityMgr.activeNetworkInfo
        }
        if (activeNetwork == null && showToast) {
            Log.d("TAG", "INTERNET NOT FOUND")
        }
        return activeNetwork != null
    }
}