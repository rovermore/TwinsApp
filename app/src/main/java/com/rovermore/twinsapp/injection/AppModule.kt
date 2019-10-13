package com.rovermore.twinsapp.injection

import android.content.Context
import com.rovermore.twinsapp.TwinsApp
import com.rovermore.twinsapp.sharedpreferences.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: TwinsApp) {

    @Provides
    @Singleton
    fun context(): Context = app.applicationContext

    @Provides
    @Singleton
    fun sharedPreferences(context: Context) = SharedPreferences(context)

}