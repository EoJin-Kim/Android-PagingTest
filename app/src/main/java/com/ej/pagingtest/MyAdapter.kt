package com.ej.pagingtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.DifferCallback
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ej.pagingtest.data.Data

class MyAdapter : PagingDataAdapter<Data, MyAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imgArea = view.findViewById<ImageView>(R.id.imgArea)
        val textArea = view.findViewById<TextView>(R.id.textArea)

        fun bind(item : Data){
            textArea.text = item._id
            imgArea.load(item.airline[0].logo)
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem._id==newItem._id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem==newItem
            }
        }
    }
}