package com.example.novaera

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCellular {
    @GET("products")
    suspend fun fetchCellular(): Response<List<Cellular>>

    @GET("details/{id}")
    suspend fun fetchBindCellular(@Path("id")id:Int): Response<ResponseBindCellular>
}