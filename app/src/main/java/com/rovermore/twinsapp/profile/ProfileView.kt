package com.rovermore.twinsapp.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.rovermore.twinsapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_view.*



class ProfileView : AppCompatActivity(), ProfileViewInterface {


    private val RC_SIGN_IN: Int = 1
    private lateinit var profilePresenterInterface: ProfilePresenterInterface
    private lateinit var gso: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)

        //val user = FirebaseAuth.getInstance().currentUser

        val alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this)

        profilePresenterInterface = ProfilePresenter(this)

        profilePresenterInterface.getGoogleSignInOptions()


        if (alreadyloggedAccount != null) {
            setProfileViewsVisible()
            button_sign_out.setOnClickListener({
                profilePresenterInterface.logoutFromClient()
            })
            profilePresenterInterface.getInforFromCurrentSession(alreadyloggedAccount)
        }

        button_sign_in.setOnClickListener({
            profilePresenterInterface.getSignIntent()
        })


    }

    override fun onReceiveGoogleSignInOptions(gso: GoogleSignInOptions) {
        this.gso = gso
        profilePresenterInterface.getSignInClient(this,gso)
    }

    override fun onSignIntentReceived(intent: Intent) {
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            profilePresenterInterface.getInfoFromResult(data)
        }
    }

    override fun onErrorFromSignInReceived() {
        Toast.makeText(
                this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
    }

    override fun onReceiveDataFromGoogleAccount(photoURL: Uri, profileName: String, profileEmail: String) {
        setProfileViewsVisible()
        Picasso.with(this).load(photoURL).into(iv_user_image)
        tv_user_name.text = profileName
        tv_user_email.text = profileEmail
    }

    override fun onLogedOutFromAccount() {
        setProfileViewsGone()
    }

    private fun setProfileViewsGone() {
        button_sign_in.visibility = View.VISIBLE
        iv_user_image.visibility = View.GONE
        tv_user_name.visibility = View.GONE
        tv_user_email.visibility = View.GONE
        button_sign_out.visibility = View.GONE
    }

    private fun setProfileViewsVisible(){
        button_sign_in.visibility = View.GONE
        iv_user_image.visibility = View.VISIBLE
        tv_user_name.visibility = View.VISIBLE
        tv_user_email.visibility = View.VISIBLE
        button_sign_out.visibility = View.VISIBLE
    }

}
