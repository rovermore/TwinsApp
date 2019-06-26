package com.rovermore.twinsapp.generalbabyview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rovermore.twinsapp.R
import com.rovermore.twinsapp.inflate
import kotlinx.android.synthetic.main.general_baby_item.view.*

class GeneralAdapter (var titleList : ArrayList<String>, var itemClicked: OnItemClicked) : RecyclerView.Adapter<GeneralAdapter.MyViewHolder>() {

    interface OnItemClicked {
        fun itemClicked(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflatedView = parent.inflate(R.layout.general_baby_item, false)
        return MyViewHolder(inflatedView, itemClicked, titleList)
    }

    override fun getItemCount() = titleList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.tv_title.text = titleList[position]
    }


    class MyViewHolder (v : View, itemClicked: GeneralAdapter.OnItemClicked, titleList : ArrayList<String>)
        : RecyclerView.ViewHolder(v), View.OnClickListener {

        var view : View = v
        var mItemClicked = itemClicked
        var mTitleList = titleList

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var position = adapterPosition
            var title = mTitleList.get(position)
            mItemClicked.itemClicked(title)

        }
    }


}