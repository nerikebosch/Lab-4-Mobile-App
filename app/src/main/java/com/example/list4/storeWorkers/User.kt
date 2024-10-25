package com.example.list4.storeWorkers

import com.example.list4.storeProducts.Product

interface User {
    //fun purchaseProduct(product: Product): Boolean
    fun returnProduct(product: Product): Boolean
}