package com.rovermore.twinsapp.generalbabyview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.rovermore.twinsapp.R
import kotlinx.android.synthetic.main.activity_general_baby_view.*

class GeneralBabyView : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_baby_view)

        recyclerView = rv_general_baby_view
        recyclerView.layoutManager = GridLayoutManager(this,2)

    }
}

