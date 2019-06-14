package com.rovermore.twinsapp

import android.app.Application

class SharedApp : Application() {
    companion object {
        lateinit var prefs: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreferences(applicationContext)
    }
}