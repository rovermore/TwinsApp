package com.rovermore.twinsapp.profile

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class ProfilePresenter(private var activity: Activity,
                       private var profileViewInterface: ProfileViewInterface):ProfilePresenterInterface {

    private val TAG = ProfilePresenter::class.java.name
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun getSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("test")
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)

        var intent = googleSignInClient.signInIntent

        profileViewInterface.onSignIntentReceived(intent)

    }

    override fun getInfoFromResult(data: Intent?) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {

            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        }
        catch (e: ApiException) {
            Log.e(TAG,"an errror ocurred ${e.message}")
            profileViewInterface.onErrorFromSignInReceived()
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {

        firebaseAuth = FirebaseAuth.getInstance()

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                    onLoggedIn(account)
            } else {
                Log.e(TAG,"an errror ocurred with login firebase")
                profileViewInterface.onErrorFromSignInReceived()
            }

        }
    }

    private fun onLoggedIn(account: GoogleSignInAccount) {

        var photoURL = account.photoUrl
        var profileName = account.displayName
        var profileEmail = account.email

        profileViewInterface.onReceiveDataFromGoogleAccount(photoURL!!,profileName!!,profileEmail!!)

    }

    //git test 2

    override fun getInforFromCurrentSession(account: GoogleSignInAccount) {
        onLoggedIn(account)
    }

    override fun logoutFromClient() {
        googleSignInClient.signOut().addOnCompleteListener({
                profileViewInterface.onLogedOutFromAccount()

        })
    }

}


