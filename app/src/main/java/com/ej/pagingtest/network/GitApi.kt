package com.ej.pagingtest.network

import com.ej.pagingtest.data.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {
    @GET("users/google/repos")
    suspend fun getData(
        @Query("page") page : Int,
        @Query("per_page") per_page : Int
    ) : Response<GithubResponse>
}