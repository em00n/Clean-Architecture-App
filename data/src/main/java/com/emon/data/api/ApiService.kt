package com.emon.data.api

import com.emon.data.model.ProductModel
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductModel>
}