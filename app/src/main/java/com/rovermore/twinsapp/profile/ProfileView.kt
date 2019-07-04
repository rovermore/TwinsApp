package com.rovermore.twinsapp.profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rovermore.twinsapp.R
import kotlinx.android.synthetic.main.activity_profile_view.*

class ProfileView : AppCompatActivity(), ProfileViewInterface {

    val RC_SIGN_IN: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)

        var profilePresenterInterface: ProfilePresenterInterface = ProfilePresenter(this, this)


        button_sign_in.setOnClickListener({
            profilePresenterInterface.getSignInClient()
        })
    }

    override fun onSignIntentReceived(intent: Intent) {
        startActivityForResult(intent, RC_SIGN_IN)
    }
}
