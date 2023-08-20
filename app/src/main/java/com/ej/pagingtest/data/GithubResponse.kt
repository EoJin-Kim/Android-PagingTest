package com.ej.pagingtest.data

data class GithubResponse (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<Items>
)