package com.rovermore.twinsapp.babyweek

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.datamodel.Test
import kotlinx.android.synthetic.main.activity_baby_week_view.*

class BabyWeekView : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: BabyWeekPagerAdapter
    private var testArrayList = arrayListOf<Test>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_week_view)
        viewPager = pager

        val myDb = FirebaseFirestore.getInstance()
        myDb.collection("test")
                .get().addOnSuccessListener {
                    it.forEach {
                        var duty: String = it.get("weekDuty") as String
                        var weekNumber: Long = it.get("weekNumber") as Long
                        var test = Test(duty, weekNumber)
                        testArrayList?.add(test)
                        pagerAdapter.updateBabyWeekPagerAdapter(testArrayList)
                    }
                }

        pagerAdapter = BabyWeekPagerAdapter(supportFragmentManager, testArrayList)
        viewPager.adapter = pagerAdapter
        var viewPagertab: SmartTabLayout = viewpagertab
        viewPagertab.setViewPager(viewPager)
    }
}
