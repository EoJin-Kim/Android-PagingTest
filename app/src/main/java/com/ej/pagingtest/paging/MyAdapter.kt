package com.ej.pagingtest.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ej.pagingtest.R
import com.ej.pagingtest.data.GithubResponse
import com.ej.pagingtest.data.GithubResponseItem

class MyAdapter : PagingDataAdapter<GithubResponseItem, MyAdapter.MyViewHolder>(DIFF_CALLBACK) {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view : View)  :RecyclerView.ViewHolder(view){
        val textArea = view.findViewById<TextView>(R.id.textArea)

        fun bind(item : GithubResponseItem){
            textArea.text = item.name
        }
    }

    companion object {
        private val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<GithubResponseItem>(){
            override fun areItemsTheSame(oldItem: GithubResponseItem, newItem: GithubResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubResponseItem, newItem: GithubResponseItem): Boolean {
                return oldItem==newItem
            }
        }
    }
}