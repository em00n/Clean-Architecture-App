package com.emon.data.extensions

import com.emon.data.model.ProductModel
import com.emon.domain.model.Product

fun ProductModel.toProduct(): Product {
    return Product(
        title, price, image
    )
}