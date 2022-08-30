package com.emon.domain.repository

import com.emon.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}