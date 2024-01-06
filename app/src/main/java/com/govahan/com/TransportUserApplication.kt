package com.govahan.com

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TransportUserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}