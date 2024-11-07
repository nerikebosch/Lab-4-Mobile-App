package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord
import java.util.Date

class Customer(
    id: Int,
    name: String,
    email: String,
    val address: String = "",
    private val store: Store
) : OnlineUser(id, name, email) {



    override fun viewProfile() {
        println("Customer Profile - ID: $id, Name: $name, Email: $email, Address: $address")
    }

    override fun viewProducts(): List<Product> {
        val listOfProduct = store.listAvailableProducts()
        return listOfProduct // Customers see only available products
    }

    override fun purchaseProduct(product: Product, quantity: Int): Boolean {
        val purchaseDate = Date().toString()
        val success = product.purchase(id, product.id, quantity, purchaseDate) // Example date
        if (success) {
            val record = PurchaseRecord(
                userId = id,
                productId = product.id,
                quantity = quantity,
                totalPrice = product.price * quantity,
                purchaseDate = purchaseDate,
                returnDate = null
            )
            purchaseHistory.add(record)
            store.addPurchase(record)
        }
        return success
    }

    fun purchaseProductById(productId: Int, quantity: Int): Boolean {
        val product = store.findProductById(productId)
        return if (product != null) {
            val purchaseDate = Date().toString()
            val success = product.purchase(id, productId, quantity, purchaseDate)
            if (success) {
                val record = PurchaseRecord(
                    userId = id,
                    productId = productId,
                    quantity = quantity,
                    totalPrice = product.price * quantity,
                    purchaseDate = purchaseDate,
                    returnDate = null )
                purchaseHistory.add(record) // Add purchase to the store's purchased list
             store.addPurchase(record) }
            success }
        else {
            println("Purchase failed: Product with ID $productId not found.")
            false
        }
    }

    override fun returnProduct(productId: Int): Boolean {
        val returnDate = Date().toString()
        // find the product (by id), make a temp to return, if found the product then return
        val product = store.findProductById(productId)
        return if (product != null) {
            val success = product.returnProduct(id, productId, returnDate)
            if (success) {
                purchaseHistory.find { it.userId == id && it.productId == productId && it.returnDate == null }?.returnDate
            }
            println("Return success")
            success

        } else {
            println("Return failed: No such product.")
            false
        }
    }
}
