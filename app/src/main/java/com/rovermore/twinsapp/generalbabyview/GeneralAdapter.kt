package com.rovermore.twinsapp.generalbabyview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.inflate
import kotlinx.android.synthetic.main.general_baby_item.view.*

class GeneralAdapter (private val titleList : ArrayList<String>) : RecyclerView.Adapter<GeneralAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflatedView = parent.inflate(R.layout.general_baby_item, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount() = titleList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.tv_title.text = titleList[position]
    }


    class MyViewHolder (v : View)  : RecyclerView.ViewHolder(v) {

        var view : View = v

    }


}