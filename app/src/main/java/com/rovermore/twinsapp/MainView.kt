package com.rovermore.twinsapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.rovermore.twinsapp.generalbabyview.GeneralBabyView
import com.rovermore.twinsapp.settings.SettingsView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_view.*

class MainView : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                intent = Intent(applicationContext, GeneralBabyView::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                intent = Intent(applicationContext, SettingsView::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val url = "https://firebasestorage.googleapis.com/v0/b/twinsapp-25d55.appspot.com/o/Foto%20Linkedin.png?alt=media&token=48813973-dca6-41a1-8a58-eea6414cbc76"

        val builder = Picasso.Builder(this)

        builder.listener(Picasso.Listener { picasso, uri, exception -> exception.printStackTrace();  })
        builder.build().load(url).into(iv_picture_download_test)
    }

}
