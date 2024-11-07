package com.example.list4.store

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeProducts.PurchaseRecord
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.OnlineUser
import com.example.list4.storeWorkers.StoreManager

class Store {
    val products = mutableListOf<Product>()
    val users = mutableListOf<OnlineUser>()
    val workers = mutableListOf<StoreManager>()
    val purchasedProducts = mutableListOf<PurchaseRecord>()


    // Add a new product to the store
    fun addProduct(product: Product) {
        products.add(product)
        println("Product ${product.item} added successfully.")
    }

    // Find product by ID
    fun findProductById(productId: Int): Product? {
        return products.find { it.id == productId }
    }

    // Remove a product by its ID
    fun removeProductById(productId: Int) {
        val product = products.find { it.id == productId }
        if (product != null) {
            products.remove(product)
            println("Product ${product.item} removed successfully")
        } else {
            println("Product with ID $productId not found.")
        }
    }

    // Update product information
    fun updateProduct(productId: Int, newPrice: Double, newQuantity: Int) {
        val product = findProductById(productId)
        if (product != null) {
            product.price = newPrice
            product.stockQuantity = newQuantity
            product.productStatus =
                if (newQuantity > 0) ProductStatus.AVAILABLE else ProductStatus.OUT_OF_STOCK
            println("Product ${product.item} updated successfully")
        } else {
            println("Product with ID $productId not found.")
        }
    }

    // List all products
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
        return products.filter { it.productStatus == ProductStatus.AVAILABLE }
            .also { availableProducts ->
                availableProducts.forEach { product ->
                    println("ID: ${product.id}, Name: ${product.item}, Price: ${product.price}, Quantity: ${product.stockQuantity}")
                }
            }
    }

    // Register a new user
    fun registerUser(user: OnlineUser) {
        users.add(user)
        println("${user.name} registered successfully as ${user::class.simpleName}.")
    }

    // Remove a user
    fun removeUserById(userId: Int, requestingUser: StoreManager) {
        if (requestingUser is Admin) {
            val user = users.find { it.id == userId }
            if (user != null) {
                users.remove(user)
                println("User ${user.name} removed successfully by Admin ${requestingUser.name}.")
            } else {
                println("User with ID $userId not found.")
            }
        } else {
            println("Permission denied: Only Admins can remove users.")
        }
    }

    // List all users
    fun listAllUsers() {
        println("Listing all users:")
        if (users.isEmpty()) {
            println("No users registered.")
        } else {
            users.forEach { user ->
                println("ID: ${user.id}, Name: ${user.name}, Email: ${user.email}")
            }
        }
    }

    // List all workers
    fun listAllWorkers() {
        println("Listing all staff:")
        if (workers.isEmpty()) {
            println("No users registered.")
        } else {
            workers.forEach { worker ->
                println("ID: ${worker.id}, Name: ${worker.name}, Type: ${worker::class.simpleName}")
            }
        }
    }

    // purchased product
    // Method for purchasing a product
    fun addPurchase(record: PurchaseRecord) {
        purchasedProducts.add(record)
    }

    // List all purchased products
    fun listAllPurchasedProducts() {
        println("Listing all purchased products:")
        if (purchasedProducts.isEmpty()) {
        println("No products have been purchased.")
        } else {
        purchasedProducts.forEach { record ->
            println("User ID: ${record.userId}, " +
                    "Product ID: ${record.productId}, " +
                    "Quantity: ${record.quantity}, " +
                    "Total Price: ${record.totalPrice}, " +
                    "Purchase Date: ${record.purchaseDate}")}
        }
    }
}
