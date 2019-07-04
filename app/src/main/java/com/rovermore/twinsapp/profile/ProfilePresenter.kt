package com.rovermore.twinsapp.profile

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class ProfilePresenter(private var activity: Activity, private var profileViewInterface: ProfileViewInterface):ProfilePresenterInterface {


    override fun getSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("test")
                .requestEmail()
                .build()

        var googleSignInClient = GoogleSignIn.getClient(activity, gso)

        var intent = googleSignInClient.signInIntent

        profileViewInterface.onSignIntentReceived(intent)

    }
}