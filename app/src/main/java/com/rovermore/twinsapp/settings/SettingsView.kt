package com.rovermore.twinsapp.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.sharedpreferences.SharedApp
import kotlinx.android.synthetic.main.activity_settings_view.*
import org.jetbrains.anko.toast

class SettingsView : AppCompatActivity(), SettingsViewInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_view)

        lateinit var familySettings : String
        lateinit var sexSettings : String
        lateinit var unitSettings : String
        lateinit var locationSettings : String

        val settingsPresenterInterface : SettingsPresenterInterface = SettingsPresenter(this)

        settingsPresenterInterface.fetchPreferences(
                spinner_familly,
                spinner_sex,
                spinner_unit,
                spinner_country)

        spinner_familly.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                familySettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_sex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sexSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unitSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                locationSettings = parent?.getItemAtPosition(position).toString()
            }
        }

        val doneButton = button_done_settings

        doneButton.setOnClickListener(View.OnClickListener {

            settingsPresenterInterface.savePreferences(et_name.text.toString(), familySettings, sexSettings, unitSettings, locationSettings)

            toast("Tu nombre es " +
                "${SharedApp.prefs.name} y eres el  ${SharedApp.prefs.familiar} de ${SharedApp.prefs.babySex}. " +
                "Mediremos su evolución en ${SharedApp.prefs.unit}, mientras estés en ${SharedApp.prefs.location} ")

            finish()})
    }

    override fun onReceivedSavedPreferences(name: String, familiarPos: Int, sexPos: Int, unitPos: Int, locationPos: Int) {
        et_name.setText(name)
        spinner_familly.setSelection(familiarPos)
        spinner_sex.setSelection(sexPos)
        spinner_unit.setSelection(unitPos)
        spinner_country.setSelection(locationPos)
    }

}
