package com.rovermore.twinsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_settings_view.*
import org.jetbrains.anko.toast

class SettingsView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_view)

        if(SharedApp.prefs.name.isNotEmpty()){
            et_name.setText(SharedApp.prefs.name)
        }

        var nameSettings : String = et_name.text.toString()
        lateinit var familySettings : String
        lateinit var sexSettings : String
        lateinit var unitSettings : String
        lateinit var locationSettings : String

        spinner_familly.setSelection(getFamilySpinnerId(SharedApp.prefs.familiar))
        spinner_sex.setSelection(getBabySexSpinnerId(SharedApp.prefs.babySex))
        spinner_unit.setSelection(getUnitSpinnerId(SharedApp.prefs.unit))
        spinner_country.setSelection(getLocationSpinnerId(SharedApp.prefs.location))

        spinner_familly.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                familySettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_sex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sexSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unitSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                locationSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        val doneButton = button_done_settings

        doneButton.setOnClickListener(View.OnClickListener {
            nameSettings = et_name.text.toString()
            SharedApp.prefs.name = nameSettings
            SharedApp.prefs.familiar = familySettings
            SharedApp.prefs.babySex = sexSettings
            SharedApp.prefs.unit = unitSettings
            SharedApp.prefs.location = locationSettings

            toast("Tu nombre es " +
                "${SharedApp.prefs.name} y eres el  ${SharedApp.prefs.familiar} de ${SharedApp.prefs.babySex}. " +
                "Mediremos su evolución en ${SharedApp.prefs.unit}, mientras estés en ${SharedApp.prefs.location} ")

            finish()})
    }

    private fun getLocationSpinnerId(location: String): Int {
        var position = 0
        for(i in 0..spinner_country.adapter.count - 1) {
            if(spinner_country.getItemAtPosition(i).toString() == location){
                position  = i
            }
        }
        return position
    }

    private fun getUnitSpinnerId(unit: String): Int {
        var position = 0
        for(i in 0..spinner_unit.adapter.count - 1) {
            if(spinner_unit.getItemAtPosition(i).toString() == unit){
                position  = i
            }
        }
        return position
    }

    private fun getBabySexSpinnerId(babySex: String): Int {
        var position = 0
        for(i in 0..spinner_sex.adapter.count - 1) {
            if(spinner_sex.getItemAtPosition(i).toString() == babySex){
                position  = i
            }
        }
        return position
    }

    private fun getFamilySpinnerId (selection: String) : Int {
        var position = 0
        for(i in 0..spinner_familly.adapter.count - 1) {
            if(spinner_familly.getItemAtPosition(i).toString() == selection){
                position  = i
            }
        }
        return position
    }
}
