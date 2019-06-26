package com.rovermore.twinsapp.generalbabyview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.babyweek.BabyWeekView
import kotlinx.android.synthetic.main.activity_general_baby_view.*

class GeneralBabyView : AppCompatActivity(), GeneralAdapter.OnItemClicked {

    override fun itemClicked(title: String) {
        when(title) {
            "Semanal"-> {
                val intent = Intent(applicationContext, BabyWeekView::class.java)
                startActivity(intent)
            }
            else-> Toast.makeText(applicationContext,"Has clicado $title",Toast.LENGTH_SHORT).show()
        }
    }

    private val titleArray = arrayListOf("Diario","Semanal","Imagenes", "Tamaño", "Calendario", "Nombres")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : GeneralAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_baby_view)

        recyclerView = rv_general_baby_view
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = GeneralAdapter(titleArray,this)
        recyclerView.adapter = adapter
    }

}

