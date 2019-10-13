package com.rovermore.twinsapp.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.rovermore.twinsapp.TwinsApp
import com.rovermore.twinsapp.sharedpreferences.SharedPreferences
import javax.inject.Inject


class ProfilePresenter(private var profileViewInterface: ProfileViewInterface):ProfilePresenterInterface {

    private val TAG = ProfilePresenter::class.java.name
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init{
        TwinsApp.daggerAppComponent().inject(this)
    }

    override fun getGoogleSignInOptions() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("91615322910-nceejsa2d9npl9mgv3g67682brs3qa41.apps.googleusercontent.com")
                .requestEmail()
                .build()
        profileViewInterface.onReceiveGoogleSignInOptions(gso)
    }

    override fun getSignInClient(activity: Activity, gso: GoogleSignInOptions) {

        googleSignInClient = GoogleSignIn.getClient(activity, gso)

    }

    override fun getSignIntent() {
        val intent = googleSignInClient.signInIntent

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

        val photoURL = account.photoUrl
        val profileName = account.displayName
        val profileEmail = account.email
        val tokenId = account.idToken

        profileViewInterface.onReceiveDataFromGoogleAccount(photoURL!!,profileName!!,profileEmail!!)

        savePreferencesFromGoogleAccount(photoURL,profileName, tokenId!!)
    }

    private fun savePreferencesFromGoogleAccount(photoURL: Uri, profileName: String, tokenId: String ) {
        sharedPreferences.name = profileName
        sharedPreferences.imageUrl = photoURL.toString()
        sharedPreferences.tokenId = tokenId
        Log.d(TAG,"The user token is ${tokenId}")
    }

    //git test 2

    override fun getInforFromCurrentSession(account: GoogleSignInAccount) {
        onLoggedIn(account)
    }

    override fun logoutFromClient() {
        googleSignInClient.signOut().addOnCompleteListener(object: OnCompleteListener<Void> {

            override fun onComplete(p0: Task<Void>) {
                profileViewInterface.onLogedOutFromAccount()
                sharedPreferences.imageUrl = ""
                sharedPreferences.tokenId = ""
                Log.d(TAG,"The user token is ${sharedPreferences.tokenId}")
            }
        })
    }

}


