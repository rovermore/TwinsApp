package com.rovermore.twinsapp.babyweek

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.datamodel.Test
import kotlinx.android.synthetic.main.activity_baby_week_view.*

class BabyWeekView : AppCompatActivity(), BabyWeekViewInterface {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: BabyWeekPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_week_view)

        viewPager = pager

        val babyWeekPresenter: BabyWeekPresenterInterface = BabyWeekPresenter(this)
        babyWeekPresenter.queryCollectionFromDatabase()
    }

    override fun onDatabaseInfoReceived(testArrayList: ArrayList<Test>) {
        pagerAdapter = BabyWeekPagerAdapter(supportFragmentManager, testArrayList)
        viewPager.adapter = pagerAdapter
        var viewPagerTab: SmartTabLayout = viewpagertab
        viewPagerTab.setViewPager(viewPager)
    }

    override fun onErrorReceivedFromDatabase(errorString: String) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
    }
}
