package com.example.list4.store

import android.os.storage.StorageManager
import com.example.list4.storeProducts.Product
import com.example.list4.storeWorkers.Customer
import com.example.list4.storeWorkers.OnlineUser
import com.example.list4.storeWorkers.User

class Store {

    val products = mutableListOf<Product>()
    val onlineUsers = mutableListOf<OnlineUser>()
    val customers = mutableListOf<Customer>()
    val managers = mutableListOf<StorageManager>()

    fun addProduct(product: Product) {
        products.add(product)
        println("${product.item} has been added to the store.")
    }

    fun listProduct() {
        if (products.isNotEmpty()) {
            println("Products in the store: ")
            for (product in products) {
                println("- ${product.item}")
            }
        }
    }
}