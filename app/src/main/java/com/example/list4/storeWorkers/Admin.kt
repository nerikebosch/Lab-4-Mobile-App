package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product

class Admin(
    id: Int,
    name: String,
    val email: String
) : OnlineUser(id, name) {

    override fun viewProfile() {
        println("Admin Profile - ID: $id, Name: $name, Email: $email")
    }

    override fun viewProducts(): List<Product> {
        return Store.getAllProducts() // Admin sees all products, including out-of-stock items
    }

    override fun purchaseProduct(product: Product, quantity: Int): Boolean {
        println("Admins do not purchase products.")
        return false
    }

    override fun returnProduct(productId: Int): Boolean {
        println("Admins do not handle returns.")
        return false
    }

    // Admin-specific methods for managing users and workers
    fun addUser(user: User): Boolean {
        return Store.addUser(user)
    }

    fun removeUser(userId: Int): Boolean {
        return Store.removeUser(userId)
    }

    fun addWorker(worker: Worker): Boolean {
        return Store.addUser(worker)
    }

    fun removeWorker(workerId: Int): Boolean {
        return Store.removeUser(workerId)
    }
}
