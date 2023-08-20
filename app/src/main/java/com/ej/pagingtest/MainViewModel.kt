package com.ej.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ej.pagingtest.data.Data
import com.ej.pagingtest.network.PassengerApi
import com.ej.pagingtest.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.getInstance().create(PassengerApi::class.java)

    val items : Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    ).flow
        .cachedIn(viewModelScope)
}