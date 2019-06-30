package com.rovermore.twinsapp.babyweek

import com.google.firebase.firestore.FirebaseFirestore
import com.rovermore.twinsapp.datamodel.Test

class BabyWeekPresenter(var babyWeekViewInterface: BabyWeekViewInterface): BabyWeekPresenterInterface {

    private var testArrayList = arrayListOf<Test>()

    override fun queryCollectionFromDatabase() {
        val myDb = FirebaseFirestore.getInstance()
        myDb.collection("test")
                .get().addOnSuccessListener {
                    it.forEach {
                        var duty: String = it.get("weekDuty") as String
                        var weekNumber: Long = it.get("weekNumber") as Long
                        var test = Test(duty, weekNumber)
                        testArrayList?.add(test)
                    }
                    if(testArrayList.isEmpty()){
                        babyWeekViewInterface.onErrorReceivedFromDatabase("Error when receiving data from server")
                    } else {
                        babyWeekViewInterface.onDatabaseInfoReceived(testArrayList)
                    }
                }
    }
}