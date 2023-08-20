package com.ej.pagingtest

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ej.pagingtest.data.Data
import com.ej.pagingtest.network.PassengerApi
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1
class MyPagingSource(
    private val passengerApi : PassengerApi
) : PagingSource<Int, Data>(){
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        Log.d("getRefreshKey","start")

        // 현재 보고있는 페이지
       val anchorPosition = state.anchorPosition
        return anchorPosition?.let {
            // prevKey -> null -> 첫번째 페이지
            // nextKey -> null -> 마지막 페이지
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: STARTING_KEY

        val response = passengerApi.getData(page, params.loadSize)

        val data = response.body()?.data

        if(page!= STARTING_KEY){
            delay(2000)
        }

        if(data!=null){
            return LoadResult.Page(
                data = data,
                prevKey = if(page == 1 ) null else page-1,
                nextKey = page + (params.loadSize / 30)
            )
        }else{
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
    }
}