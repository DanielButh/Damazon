package com.example.damazon.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.damazon.model.Product
import com.example.damazon.network.StoreRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel: ViewModel() {
    private val repository = StoreRepository()
    private val _loaderState = MutableLiveData<Boolean>()
    val loaderState: LiveData<Boolean>
        get() = _loaderState
    private val _productInfo = MutableLiveData<Product>()
    val productInfo: LiveData<Product>
        get() = _productInfo

    fun getProductDetail() {
        _loaderState.value = true // se actualiza el publisher
        viewModelScope.launch { // corrutina. Lo que esté adentro de estas llaves se va a ejecutar fuera del hilo principal
            val response = repository.getProductDetail() // tendrá el body de StoreRepository
            _loaderState.value = false
            response?.let {
                _productInfo.value = it // it: nuevo producto que obtuve de retrofit
            } ?: run {
                Log.e("API ERROR", "NO SE PUDO COMPLETAR LA PETICIÓN")
            }
        }
    }

}