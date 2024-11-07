package com.example.list4.store

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeProducts.PurchaseRecord
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.OnlineUser
import com.example.list4.storeWorkers.StoreManager

/**
 * Manages store operations including products, users, and purchase records.
 */
class Store {
    /** List of products available in the store */
    val products = mutableListOf<Product>()

    /** List of registered users in the store */
    val users = mutableListOf<OnlineUser>()

    /** List of workers (store managers, staff) in the store */
    val workers = mutableListOf<StoreManager>()

    /** List of all purchase records in the store */
    val purchasedProducts = mutableListOf<PurchaseRecord>()


    /**
     * Adds a new product to the store inventory.
     * @param product The product to be added
     */
    fun addProduct(product: Product) {
        products.add(product)
        println("Product ${product.item} added successfully.")
    }

    /**
     * Finds a product by its ID.
     * @param productId The ID of the product to find
     * @return The product if found, or null if not found
     */
    fun findProductById(productId: Int): Product? {
        return products.find { it.id == productId }
    }

    /**
     * Removes a product by its ID.
     * @param productId The ID of the product to remove
     */
    fun removeProductById(productId: Int) {
        val product = products.find { it.id == productId }
        if (product != null) {
            products.remove(product)
            println("Product ${product.item} removed successfully")
        } else {
            println("Product with ID $productId not found.")
        }
    }

    /**
     * Updates the details of a product, including price and stock quantity.
     * @param productId The ID of the product to update
     * @param newPrice The new price of the product
     * @param newQuantity The new stock quantity of the product
     */
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

    /**
     * Lists all products, including out-of-stock items.
     * @return List of all products
     */
    fun listAllProducts(): List<Product> {
        println("Listing all products (including out-of-stock):")
        products.forEach { product ->
            println("ID: ${product.id}, Name: ${product.item}, Price: ${product.price}, Stock: ${product.stockQuantity}, Status: ${product.productStatus}")
        }
        return products
    }

    /**
     * Lists all available products in stock.
     * @return List of available products
     */
    fun listAvailableProducts(): List<Product> {
        println("Listing all available products:")
        return products.filter { it.productStatus == ProductStatus.AVAILABLE }
            .also { availableProducts ->
                availableProducts.forEach { product ->
                    println("ID: ${product.id}, Name: ${product.item}, Price: ${product.price}, Quantity: ${product.stockQuantity}")
                }
            }
    }

    /**
     * Registers a new user to the store.
     * @param user The user to be registered
     */
    fun registerUser(user: OnlineUser) {
        users.add(user)
        println("${user.name} registered successfully as ${user::class.simpleName}.")
    }

    /**
     * Removes a user from the store by their ID. Only an Admin can perform this action.
     * @param userId The ID of the user to be removed
     * @param requestingUser The Admin requesting the user removal
     */
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

    /**
     * Lists all registered users in the store.
     */
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

    /**
     * Lists all workers in the store.
     */
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

    /**
     * Adds a purchase record to the list of purchased products.
     * @param record The purchase record to add
     */
    fun addPurchase(record: PurchaseRecord) {
        purchasedProducts.add(record)
    }

    /**
     * Lists all purchased products with details.
     */
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
