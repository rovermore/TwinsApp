package com.rovermore.twinsapp.profile

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

interface ProfilePresenterInterface {

    fun getGoogleSignInOptions()

    fun getSignInClient(activity: Activity, gso: GoogleSignInOptions)

    fun getSignIntent()

    fun getInfoFromResult(data: Intent?)

    fun getInforFromCurrentSession(account: GoogleSignInAccount)

    fun logoutFromClient()


}