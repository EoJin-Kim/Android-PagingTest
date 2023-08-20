package com.ej.pagingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel :MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rv)
        val myAdapter = MyAdapter()

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter

        lifecycleScope.launch {
            viewModel.items.collect{
                myAdapter.submitData(it)
            }
        }
    }
}