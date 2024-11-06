package com.example.list4.store

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.OnlineUser
import com.example.list4.storeWorkers.StoreManager

class Store {
    val products = mutableListOf<Product>()
    val users = mutableListOf<OnlineUser>()
    val workers = mutableListOf<StoreManager>()

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

//    // Purchase a product
//    fun purchaseProduct(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
//        val user = users.find { it.id == userId }
//        val product = findProductById(productId)
//
//        return if (user != null && user is Customer && product != null) {
//            if (product.purchase(userId, quantity, purchaseDate) ) {
//                val purchaseRecord = PurchaseRecord(
//                    userId = userId,
//                    productId = productId,
//                    quantity = quantity,
//                    totalPrice = product.price * quantity,
//                    purchaseDate = purchaseDate,
//                    returnDate = null
//                )
//                user.purchaseHistory.add(purchaseRecord)
//                println("Purchase successful for Product ${product.item} by User ${user.name}.")
//                true
//            } else {
//                println("Purchase failed for Product ${product.item}.")
//                false
//            }
//        } else {
//            println("User or Product not found, or User is not authorized.")
//            false
//        }
//    }
//
//    // Return a product
//    fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
//        val user = users.find { it.id == userId }
//        val product = findProductById(productId)
//
//        return if (user != null && user is Customer && product != null) {
//            if (product.returnProduct(userId, productId, returnDate)) {
//                user.purchaseHistory.find { it.productId == productId && it.returnDate == null }?.returnDate = returnDate
//                println("Return successful for Product ${product.item} by User ${user.name}.")
//                true
//            } else {
//                println("Return failed for Product ${product.item}.")
//                false
//            }
//        } else {
//            println("User or Product not found, or User is not authorized.")
//            false
//        }
//    }

    // Update product information
    fun updateProduct(productId: Int, newPrice: Double, newQuantity: Int) {
        val product = findProductById(productId)
        if (product != null) {
            product.price = newPrice
            product.stockQuantity = newQuantity
            product.productStatus = if (newQuantity > 0) ProductStatus.AVAILABLE else ProductStatus.OUT_OF_STOCK
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
        return products.filter { it.productStatus == ProductStatus.AVAILABLE }.also { availableProducts ->
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
        println("Listing all staffs:")
        if (workers.isEmpty()) {
            println("No users registered.")
        } else {
            workers.forEach { worker ->
                println("ID: ${worker.id}, Name: ${worker.name}, Type: ${worker::class.simpleName}")
            }
        }
    }

}
