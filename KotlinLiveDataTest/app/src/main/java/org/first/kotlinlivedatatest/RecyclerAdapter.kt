package org.first.kotlinlivedatatest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*

class RecyclerAdapter(var data: LiveData<ArrayList<User>>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
    ) {
        var tv1 = itemView.firstTV
        var tv2 = itemView.secondTV
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun getItemCount(): Int {
        Log.e("datasize", "" + data.value!!.size)
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.e("dataValue1", data.value!!.get(0).toString())
        Log.e("dataValue2", data.value!!.get(1).toString())
        Log.e("dataValue3", data.value!!.get(2).toString())

        data.value!!.get(position).let { item ->
            with(holder) {
                tv1.text = item.name
                tv2.text = item.nickname
                Log.e("TextSet", tv1.text.toString() + tv2.text.toString())
            }
        }
    }
}