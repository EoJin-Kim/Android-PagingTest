package com.ej.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ej.pagingtest.data.Items
import com.ej.pagingtest.network.GitApi
import com.ej.pagingtest.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel(val str : String) : ViewModel() {
    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<Items>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            GithubPagingSource(api,str)
        }
    )
        .flow
        .cachedIn(viewModelScope)

}