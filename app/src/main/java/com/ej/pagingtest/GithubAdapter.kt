package com.ej.pagingtest

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ej.pagingtest.data.Items

class GithubAdapter : PagingDataAdapter<Items, GithubViewHolder>(DIFF_CALLBACK){

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val items = getItem(position)
        if(items!=null){
            holder.bind(items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder.create(parent)
    }

    companion object{
        private val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<Items>(){
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }
        }
    }
}