package com.example.list4.storeWorkers

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord

abstract class OnlineUser(
    val id: Int,
    val name: String
) : User {
    // Protected property for holding purchase history
    protected val purchaseHistory = mutableListOf<PurchaseRecord>()

    // Implementation of viewing purchase history, common to all users
    override fun viewPurchaseHistory(): List<PurchaseRecord> = purchaseHistory

    // Abstract methods to be implemented by subclasses based on their roles
    abstract override fun viewProfile()
    abstract override fun viewProducts(): List<Product>
    abstract override fun purchaseProduct(product: Product, quantity: Int): Boolean
    abstract override fun returnProduct(productId: Int): Boolean
}
