package com.rovermore.twinsapp.settings

import android.widget.Spinner

interface SettingsPresenterInterface {

    fun savePreferences(name: String, familiar: String, sex: String, unit: String, location: String)

    fun fetchPreferences(familiarSpinner: Spinner,
                         sexSpinner: Spinner,
                         unitSpinner: Spinner,
                         locationSpinner: Spinner)

}