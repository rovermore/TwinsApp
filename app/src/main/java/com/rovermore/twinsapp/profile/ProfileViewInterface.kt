package com.rovermore.twinsapp.profile

import android.content.Intent
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

interface ProfileViewInterface {

    fun onReceiveGoogleSignInOptions(gso: GoogleSignInOptions)

    fun onSignIntentReceived(intent: Intent)

    fun onErrorFromSignInReceived()

    fun onReceiveDataFromGoogleAccount(photoURL: Uri, profileName: String, profileEmail: String)

    fun onLogedOutFromAccount()
}