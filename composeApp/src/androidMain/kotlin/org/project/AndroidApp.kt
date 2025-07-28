package org.project

import android.app.Application
import android.content.Context


object AndroidApp {
    lateinit var aplicationContext: Context
}

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidApp.aplicationContext = this
    }
}