package com.rovermore.twinsapp

import android.app.Application
import com.rovermore.twinsapp.injection.AppComponent
import com.rovermore.twinsapp.injection.AppModule
import com.rovermore.twinsapp.injection.DaggerAppComponent

class TwinsApp : Application() {

    companion object {
        lateinit var mDaggerAppComponent: AppComponent
        fun daggerAppComponent():AppComponent = mDaggerAppComponent
    }


    override fun onCreate() {
        super.onCreate()
        mDaggerAppComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

    }
}