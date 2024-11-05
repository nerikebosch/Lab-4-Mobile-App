package com.example.list4.store

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.OnlineUser
import com.example.list4.storeWorkers.Customer
import com.example.list4.storeWorkers.Worker

class Store {
    private val products = mutableListOf<Product>()
    private val users = mutableListOf<OnlineUser>()

    // Add a new product to the store
    fun addProduct(product: Product) {
        products.add(product)
        println("Product ${product.item} added successfully.")
    }

    // Find product by ID
    private fun findProductById(productId: Int): Product? {
        return products.find { it.id == productId }
    }

    // Method to remove a product by its ID
    fun removeProduct(productId: Int, user: OnlineUser) {
        if (user is Admin || user is Worker) {
            val product = products.find { it.id == productId }
            if (product != null) {
                products.remove(product)
                println("Product ${product.item} removed successfully by ${user.name}")
            } else {
                println("Product with ID $productId not found.")
            }
        } else {
            println("Permission denied: Only Admins or Workers can remove products.")
        }
    }

    // Purchase a product
    fun purchaseProduct(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val user = users.find { it.id == userId }
        val product = findProductById(productId)

        return if (user != null && user is Customer && product != null) {
            if (product.purchase(userId, quantity, purchaseDate)) {
                val purchaseRecord = PurchaseRecord(
                    userId = userId,
                    productId = productId,
                    quantity = quantity,
                    totalPrice = product.price * quantity,
                    purchaseDate = purchaseDate,
                    returnDate = null
                )
                user.purchaseHistory.add(purchaseRecord)
                println("Purchase successful for Product ${product.item} by User ${user.name}.")
                true
            } else {
                println("Purchase failed for Product ${product.item}.")
                false
            }
        } else {
            println("User or Product not found, or User is not authorized.")
            false
        }
    }

    // Return a product
    fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val user = users.find { it.id == userId }
        val product = findProductById(productId)

        return if (user != null && user is Customer && product != null) {
            if (product.returnProduct(userId, productId, returnDate)) {
                user.purchaseHistory.find { it.productId == productId && it.returnDate == null }?.returnDate = returnDate
                println("Return successful for Product ${product.item} by User ${user.name}.")
                true
            } else {
                println("Return failed for Product ${product.item}.")
                false
            }
        } else {
            println("User or Product not found, or User is not authorized.")
            false
        }
    }

    // Update product information
    fun updateProduct(productId: Int, newPrice: Double, newQuantity: Int, user: OnlineUser) {
        if (user is Admin || user is Worker) {
            val product = findProductById(productId)
            if (product != null) {
                product.price = newPrice
                product.stockQuantity = newQuantity
                product.productStatus = if (newQuantity > 0) ProductStatus.AVAILABLE else ProductStatus.OUT_OF_STOCK
                println("Product ${product.item} updated successfully by ${user.name}")
            } else {
                println("Product with ID $productId not found.")
            }
        } else {
            println("Permission denied: Only Admins or Employees can update products.")
        }
    }

    fun listAllProducts(): List<Product> {
        println("Listing all products (including out-of-stock):")
        products.forEach { product ->
            println("ID: ${product.id}, Name: ${product.item}, Price: ${product.price}, Stock: ${product.stockQuantity}, Status: ${product.productStatus}")
        }
        return products
    }

    // List all available products
    fun listAvailableProducts(): List<Product> {
        println("Listing all available products:")
        return products.filter { it.productStatus == ProductStatus.AVAILABLE }.also { availableProducts ->
            availableProducts.forEach { product ->
                println("ID: ${product.id}, Name: ${product.item}, Price: ${product.price}, Quantity: ${product.stockQuantity}")
            }
        }
    }

    // Additional user and product management methods (registering users, updating product info, etc.)
}
