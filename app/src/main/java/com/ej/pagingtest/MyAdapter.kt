package com.ej.pagingtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : PagingDataAdapter<User, MyAdapter.UserViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return UserViewHolder(view)
    }

    class UserViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val id = view.findViewById<TextView>(R.id.id)
        val userName = view.findViewById<TextView>(R.id.userName)
        fun bind(item : User){
            id.text = item.id.toString()
            userName.text = item.userName
        }
    }
}