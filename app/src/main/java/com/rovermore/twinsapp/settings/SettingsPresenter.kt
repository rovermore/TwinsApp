package com.rovermore.twinsapp.settings

import android.widget.Spinner
import com.rovermore.twinsapp.TwinsApp
import com.rovermore.twinsapp.sharedpreferences.SharedPreferences
import javax.inject.Inject

class SettingsPresenter(var settingsViewInterface: SettingsViewInterface) : SettingsPresenterInterface {

    //private lateinit var nameSettings : String
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init{
        TwinsApp.daggerAppComponent().inject(this)
    }

    override fun savePreferences(name: String, familiar: String, sex: String, unit: String, location: String) {
        if(name.isNotEmpty()){
            sharedPreferences.name = name}
        sharedPreferences.familiar = familiar
        sharedPreferences.babySex = sex
        sharedPreferences.unit = unit
        sharedPreferences.location = location
    }

    override fun fetchPreferences(familiarSpinner: Spinner, sexSpinner: Spinner,
                                  unitSpinner: Spinner, locationSpinner: Spinner) {
        //getName()
        var familyPosition = getFamilySpinnerId(sharedPreferences.familiar,familiarSpinner)
        var sexPosition = getBabySexSpinnerId(sharedPreferences.babySex, sexSpinner)
        var unitPosition = getUnitSpinnerId(sharedPreferences.unit, unitSpinner)
        var locationPosition = getLocationSpinnerId(sharedPreferences.location, locationSpinner)

        var photoUrl = sharedPreferences.imageUrl
        var nameSettings = sharedPreferences.name
        settingsViewInterface.onReceivedSavedPreferences(nameSettings,familyPosition, sexPosition,
                unitPosition, locationPosition, photoUrl)
    }

    /*private fun getName() {
        if(TwinsApp.prefs.name.isNotEmpty()){
            nameSettings = TwinsApp.prefs.name
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