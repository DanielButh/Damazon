package com.example.damazon.core


import com.example.damazon.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface StoreAPI {
    @GET("products/1")
    suspend fun getProductDetail():Response<Product>
}