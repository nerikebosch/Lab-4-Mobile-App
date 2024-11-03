package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord

class Customer(
    id: Int,
    name: String,
    val email: String,
    val address: String
) : OnlineUser(id, name) {

    override fun viewProfile() {
        println("Customer Profile - ID: $id, Name: $name, Email: $email, Address: $address")
    }

    override fun viewProducts(): List<Product> {
        return Store.getAvailableProducts() // Customers see only available products
    }

    override fun purchaseProduct(product: Product, quantity: Int): Boolean {
        val success = product.purchase(id, product.id, quantity, purchaseDate = "2024-11-03") // Example date
        if (success) {
            val record = PurchaseRecord(
                userId = id,
                productId = product.id,
                quantity = quantity,
                totalPrice = product.price * quantity,
                purchaseDate = "2024-11-03", // Example date
                returnDate = null
            )
            purchaseHistory.add(record)
        }
        return success
    }

    override fun returnProduct(productId: Int): Boolean {
        val product = Store.getProductById(productId)
        return if (product != null) {
            val success = product.returnProduct(id, productId, returnDate = "2024-11-03") // Example date
            if (success) {
                purchaseHistory.find { it.productId == productId && it.returnDate == null }?.returnDate = "2024-11-03"
            }
            success
        } else {
            println("Return failed: No such product.")
            false
        }
    }
}
