package com.rovermore.twinsapp.babyweek

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class BabyWeekPagerAdapter(fragmentManager: FragmentManager, private val week: ArrayList<Int>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return FragmentWeek.newInstance(week[position].toString())
    }

    // 3
    override fun getCount(): Int {
        return week.size
    }
}