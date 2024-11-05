package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product

class Worker(
    id: Int,
    name: String,
    val email: String,
    private val store: Store
) : OnlineUser(id, name) {

    override fun viewProfile() {
        println("Worker Profile - ID: $id, Name: $name, Email: $email")
    }

    // Workers can view all products in the store
    override fun viewProducts(): List<Product> {
        return store.listAllProducts() // Modified to list all, including out-of-stock items
    }

    // Workers do not make personal purchases in this role
    override fun purchaseProduct(product: Product, quantity: Int): Boolean {
        println("Workers are not allowed to make personal purchases.")
        return false
    }

    // Workers cannot handle returns directly in this role
    override fun returnProduct(productId: Int): Boolean {
        println("Workers are not authorized to process returns.")
        return false
    }

    // Worker-specific methods for product management through Store

    // Adds a new product through the Store, logging the action
    fun addProduct(product: Product) {
        store.addProduct(product, this)
    }

    // Removes a product through the Store, logging the action
    fun removeProduct(productId: Int) {
        store.removeProduct(productId, this)
    }

    // Updates product details (price and quantity) through the Store
    fun updateProductDetails(productId: Int, newPrice: Double, newQuantity: Int) {
        store.updateProduct(productId, newPrice, newQuantity, this)
    }
}
