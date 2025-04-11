package com.example.damazon.network

import android.util.Log
import com.example.damazon.core.RetrofitInstance
import com.example.damazon.core.StoreAPI
import com.example.damazon.model.Product

class StoreRepository {
    private val retrofit =RetrofitInstance.getRetrofit().create(StoreAPI::class.java)

    suspend fun getProductDetail(): Product? { // Producto opcional
        val response = retrofit.getProductDetail()
        Log.i("RESPONSE", response.body().toString())

        return response.body()
    }
}