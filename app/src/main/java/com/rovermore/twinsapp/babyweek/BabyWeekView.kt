package com.rovermore.twinsapp.babyweek

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.rovermore.twinsapp.R
import kotlinx.android.synthetic.main.activity_baby_week_view.*

class BabyWeekView : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: BabyWeekPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_week_view)
        viewPager = pager

        pagerAdapter = BabyWeekPagerAdapter(supportFragmentManager, week = arrayListOf<Int>(1,2,3,4,5,6,7,8,9))
        viewPager.adapter = pagerAdapter

        var viewPagertab: SmartTabLayout = viewpagertab
        viewPagertab.setViewPager(viewPager)

    }
}
