package com.rovermore.twinsapp.settings

import android.widget.Spinner
import com.rovermore.twinsapp.sharedpreferences.SharedApp

class SettingsPresenter(var settingsViewInterface: SettingsViewInterface) : SettingsPresenterInterface {

    //private lateinit var nameSettings : String

    override fun savePreferences(name: String, familiar: String, sex: String, unit: String, location: String) {
        if(name.isNotEmpty()){
        SharedApp.prefs.name = name}
        SharedApp.prefs.familiar = familiar
        SharedApp.prefs.babySex = sex
        SharedApp.prefs.unit = unit
        SharedApp.prefs.location = location
    }

    override fun fetchPreferences(familiarSpinner: Spinner, sexSpinner: Spinner,
                                  unitSpinner: Spinner, locationSpinner: Spinner) {
        //getName()
        var familyPosition = getFamilySpinnerId(SharedApp.prefs.familiar,familiarSpinner)
        var sexPosition = getBabySexSpinnerId(SharedApp.prefs.babySex, sexSpinner)
        var unitPosition = getUnitSpinnerId(SharedApp.prefs.unit, unitSpinner)
        var locationPosition = getLocationSpinnerId(SharedApp.prefs.location, locationSpinner)

        var photoUrl = SharedApp.prefs.imageUrl
        var nameSettings = SharedApp.prefs.name
        settingsViewInterface.onReceivedSavedPreferences(nameSettings,familyPosition, sexPosition,
                unitPosition, locationPosition, photoUrl)
    }

    /*private fun getName() {
        if(SharedApp.prefs.name.isNotEmpty()){
            nameSettings = SharedApp.prefs.name
        }
    }*/

    private fun getLocationSpinnerId(location: String, locationSpinner: Spinner): Int {
        var position = 0
        for(i in 0..locationSpinner.adapter.count - 1) {
            if(locationSpinner.getItemAtPosition(i).toString() == location){
                position  = i
            }
        }
        return position
    }

    private fun getUnitSpinnerId(unit: String, unitSpinner: Spinner): Int {
        var position = 0
        for(i in 0..unitSpinner.adapter.count - 1) {
            if(unitSpinner.getItemAtPosition(i).toString() == unit){
                position  = i
            }
        }
        return position
    }

    private fun getBabySexSpinnerId(babySex: String, sexSpinner: Spinner): Int {
        var position = 0
        for(i in 0..sexSpinner.adapter.count - 1) {
            if(sexSpinner.getItemAtPosition(i).toString() == babySex){
                position  = i
            }
        }
        return position
    }

    private fun getFamilySpinnerId (selection: String, familiarSpinner: Spinner) : Int {
        var position = 0
        for(i in 0..familiarSpinner.adapter.count - 1) {
            if(familiarSpinner.getItemAtPosition(i).toString() == selection){
                position  = i
            }
        }
        return position
    }

}