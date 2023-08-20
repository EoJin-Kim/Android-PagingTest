package com.ej.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ej.pagingtest.data.GithubResponseItem
import com.ej.pagingtest.network.GitApi
import com.ej.pagingtest.network.RetrofitInstance
import com.ej.pagingtest.paging.MyPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<GithubResponseItem>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}