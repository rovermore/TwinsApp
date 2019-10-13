package com.rovermore.twinsapp.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.TwinsApp
import com.rovermore.twinsapp.profile.ProfileView
import com.rovermore.twinsapp.sharedpreferences.SharedPreferences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_settings_view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class SettingsView : AppCompatActivity(), SettingsViewInterface {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_view)

        TwinsApp.daggerAppComponent().inject(this)

        lateinit var familySettings : String
        lateinit var sexSettings : String
        lateinit var unitSettings : String
        lateinit var locationSettings : String

        val settingsPresenterInterface : SettingsPresenterInterface = SettingsPresenter(this)

        iv_profile.setOnClickListener({
            val intent = Intent(applicationContext, ProfileView::class.java)
            startActivity(intent)
        })

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
                "${sharedPreferences.name} y eres el  ${sharedPreferences.familiar} de ${sharedPreferences.babySex}. " +
                "Mediremos su evolución en ${sharedPreferences.unit}, mientras estés en ${sharedPreferences.location} ")

            finish()})
    }

    override fun onReceivedSavedPreferences(name: String, familiarPos: Int, sexPos: Int,
                                            unitPos: Int, locationPos: Int, photoUrl: String) {
        et_name.setText(name)
        spinner_familly.setSelection(familiarPos)
        spinner_sex.setSelection(sexPos)
        spinner_unit.setSelection(unitPos)
        spinner_country.setSelection(locationPos)

        if(photoUrl.isNotBlank()){
            Picasso.with(this).load(photoUrl).into(iv_profile)
        } else {
            iv_profile.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }
    }

}
