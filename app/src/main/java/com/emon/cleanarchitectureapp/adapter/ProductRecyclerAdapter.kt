package com.emon.cleanarchitectureapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emon.cleanarchitectureapp.databinding.ProductRowBinding
import com.emon.domain.model.Product

class ProductRecyclerAdapter(val productList: List<Product>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.productTitle.text = product.title
        holder.binding.productPrice.text = product.price.toString()
        holder.binding.productImage.load(product.image) {

        }
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(val binding: ProductRowBinding) : RecyclerView.ViewHolder(binding.root)
}