package com.rovermore.twinsapp.settings

interface SettingsViewInterface {

    fun onReceivedSavedPreferences(name: String, familiarPos: Int, sexPos: Int, unitPos: Int, locationPos: Int)

}