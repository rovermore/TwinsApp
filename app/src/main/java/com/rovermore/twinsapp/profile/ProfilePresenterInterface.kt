package com.rovermore.twinsapp.profile

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ProfilePresenterInterface {

    fun getSignInClient()

    fun getInfoFromResult(data: Intent?)

    fun getInforFromCurrentSession(account: GoogleSignInAccount)

    fun logoutFromClient()


}