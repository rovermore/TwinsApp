package com.rovermore.twinsapp.firebase

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseDb : Application() {
    companion object {
        lateinit var myDb: FirebaseFirestore
    }

    override fun onCreate() {
        super.onCreate()
        myDb = FirebaseFirestore.getInstance()
    }
}