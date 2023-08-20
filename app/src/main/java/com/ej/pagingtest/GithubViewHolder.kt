package com.ej.pagingtest

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ej.pagingtest.data.Items

class GithubViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val nameArea = view.findViewById<TextView>(R.id.nameArea)
    val url = view.findViewById<TextView>(R.id.url)

    private var items : Items? = null

    init {
        view.setOnClickListener {
            items?.url.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(items: Items){
        nameArea.text=items.name
        url.text = items.url

        this.items = items
    }

    companion object{
        fun create(parent : ViewGroup) :GithubViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.github_item, parent, false)
            return GithubViewHolder(view)
        }
    }
}