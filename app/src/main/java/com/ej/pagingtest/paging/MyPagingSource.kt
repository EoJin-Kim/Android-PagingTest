package com.ej.pagingtest.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ej.pagingtest.data.GithubResponseItem
import com.ej.pagingtest.network.GitApi
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1

class MyPagingSource(
    private val githubService : GitApi
) : PagingSource<Int, GithubResponseItem>(){

    init {
        Log.d("MyPagingSource","init")
    }
    override fun getRefreshKey(state: PagingState<Int, GithubResponseItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponseItem> {
        val page = params.key ?: STARTING_KEY

        val response = githubService.getData(page, params.loadSize)

        val data = response.body()

        if (page != STARTING_KEY) {
            delay(3000)
        }
        if (data != null) {
            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = page + (params.loadSize / 30)
            )
        } else {
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
    }
}