package com.emon.data.repository

import com.emon.data.api.ApiService
import com.emon.data.extensions.toProduct
import com.emon.domain.model.Product
import com.emon.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().map { it.toProduct() }
    }
}