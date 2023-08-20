package com.ej.pagingtest.data

data class PassengerResponse(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)