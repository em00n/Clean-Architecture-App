package com.emon.cleanarchitectureapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emon.cleanarchitectureapp.model.ProductListState
import com.emon.domain.common.Resource
import com.emon.domain.use_case.get_products.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ProductListState>()
    val state: LiveData<ProductListState> = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        productUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ProductListState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        ProductListState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}