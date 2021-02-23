package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.NetworkModule
import com.example.myapplication.di.RepositoryModule
import com.example.myapplication.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import java.util.logging.Logger

class FirstApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            koin._logger=koinLogger()
            androidContext(this@FirstApplication)
            modules(getModule())
        }
    }
     private fun koinLogger(): org.koin.core.logger.Logger {
        return if (false)
            org.koin.android.logger.AndroidLogger() else
            EmptyLogger()
    }

    private fun getModule():List<Module>{
        return listOf(
                NetworkModule,
            RepositoryModule,
            ViewModelModule
        )
    }

}