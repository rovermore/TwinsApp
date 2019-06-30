package com.rovermore.twinsapp.babyweek

import com.rovermore.twinsapp.datamodel.Test

interface BabyWeekViewInterface {

    fun onDatabaseInfoReceived(testArrayList: ArrayList<Test>)

    fun onErrorReceivedFromDatabase(errorString: String)
}