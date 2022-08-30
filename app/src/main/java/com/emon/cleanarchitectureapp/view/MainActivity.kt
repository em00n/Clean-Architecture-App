package com.emon.cleanarchitectureapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.emon.cleanarchitectureapp.adapter.ProductRecyclerAdapter
import com.emon.cleanarchitectureapp.databinding.ActivityMainBinding
import com.emon.cleanarchitectureapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductRecyclerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressBar.visibility = View.INVISIBLE
                if (it.error.isNotBlank()) {
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = it.error
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    productAdapter = ProductRecyclerAdapter(it.products)
                    binding.recyclerView.adapter = productAdapter
                }
            }
        }
    }
}