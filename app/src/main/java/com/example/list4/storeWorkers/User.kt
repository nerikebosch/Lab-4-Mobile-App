package com.example.list4.storeWorkers

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord

interface User {

    fun viewProfile()
    fun viewProducts(): List<Product>
    fun viewPurchaseHistory(): List<PurchaseRecord>
    fun purchaseProduct(product: Product, quantity: Int): Boolean
    fun returnProduct(productId: Int): Boolean
}