package com.rovermore.twinsapp.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences (context: Context){

    val SHARED_PREFERENCES_NAME = "shared_preferences_twin_app"
    val SHARED_PREF_IMAGE_URL = "image_url"
    val SHARED_PREF_NAME = "name"
    val SHARED_PREF_FAMILIAR = "familiar"
    val SHARED_PREF_BABY_SEX = "baby_sex"
    val SHARED_PREF_UNIT = "unit"
    val SHARED_PREF_LOCATION = "location"

    val sharedPreferences : SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)

    var imageUrl: String
        get() = sharedPreferences.getString(SHARED_PREF_IMAGE_URL, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_IMAGE_URL, value).apply()

    var name: String
        get() = sharedPreferences.getString(SHARED_PREF_NAME, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_NAME, value).apply()

    var familiar: String
        get() = sharedPreferences.getString(SHARED_PREF_FAMILIAR, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_FAMILIAR, value).apply()

    var babySex: String
        get() = sharedPreferences.getString(SHARED_PREF_BABY_SEX, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_BABY_SEX, value).apply()

    var unit: String
        get() = sharedPreferences.getString(SHARED_PREF_UNIT, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_UNIT, value).apply()

    var location: String
        get() = sharedPreferences.getString(SHARED_PREF_LOCATION, "")
        set(value) = sharedPreferences.edit().putString(SHARED_PREF_LOCATION, value).apply()
}