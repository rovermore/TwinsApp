package com.rovermore.twinsapp.profile

import android.content.Intent
import android.net.Uri

interface ProfileViewInterface {

    fun onSignIntentReceived(intent: Intent)

    fun onErrorFromSignInReceived()

    fun onReceiveDataFromGoogleAccount(photoURL: Uri, profileName: String, profileEmail: String)

    fun onLogedOutFromAccount()
}