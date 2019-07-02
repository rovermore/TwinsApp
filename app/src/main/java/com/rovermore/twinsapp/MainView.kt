package com.rovermore.twinsapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.rovermore.twinsapp.generalbabyview.GeneralBabyView
import com.rovermore.twinsapp.settings.SettingsView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_view.*




class MainView : AppCompatActivity() {

    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f

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

        val url = "https://firebasestorage.googleapis.com/v0/b/twinsapp-25d55.appspot.com/o/674609.jpg?alt=media&token=62b2c073-99e3-481b-9958-fcb471af812f"

        val builder = Picasso.Builder(this)

        builder.listener(Picasso.Listener { picasso, uri, exception -> exception.printStackTrace();  })
        builder.build().load(url).into(iv_picture_download_test)

        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f))
            iv_picture_download_test.scaleX = mScaleFactor
            iv_picture_download_test.scaleY = mScaleFactor
            return true
        }
    }
}
