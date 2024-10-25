package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus

interface Worker {
    fun viewProfile()
    fun addProduct(product: Product, store: Store)
    fun deleteProduct(product: Product, store: Store)
    fun changeProductStatus(product: Product, store: Store, status: ProductStatus)
}