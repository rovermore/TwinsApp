package com.rovermore.twinsapp.babyweek

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rovermore.twinsapp.datamodel.Test

class BabyWeekPagerAdapter(fragmentManager: FragmentManager, private var weekArrayList: ArrayList<Test>?) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return FragmentWeek.newInstance(weekArrayList?.get(position)!!.weekNumber.toInt(),weekArrayList?.get(position)!!.duty)
    }

    // 3
    override fun getCount(): Int {
        var size = weekArrayList!!.size
        if(size!=0)return size
        return 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return weekArrayList!!.get(position).weekNumber.toString()
    }

    fun updateBabyWeekPagerAdapter(updatedWeekArrayList: ArrayList<Test>?){
        weekArrayList = updatedWeekArrayList
        this.notifyDataSetChanged()
    }
}