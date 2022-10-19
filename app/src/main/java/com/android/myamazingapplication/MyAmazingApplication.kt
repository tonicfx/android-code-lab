package com.android.myamazingapplication

import android.app.Application
import com.android.myamazingapplication.core.appModule
import com.android.myamazingapplication.core.ktorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyAmazingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@MyAmazingApplication)
            modules(ktorModule)
            modules(appModule)
        }
    }
}