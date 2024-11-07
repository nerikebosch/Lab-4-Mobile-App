package com.example.list4.storeWorkers

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord

/**
 * Represents an online user (Customer or Admin) who can view and interact with products,
 * make purchases, and view purchase history.
 * @param id Unique identifier of the user
 * @param name Name of the user
 * @param email Email of the user
 */
abstract class OnlineUser(
    val id: Int,
    val name: String,
    val email: String
) : User {
    // Protected property for holding purchase history
    val purchaseHistory = mutableListOf<PurchaseRecord>()

    // Implementation of viewing purchase history, common to all users
    /**
     * Views the purchase history for the user.
     * @return A list of purchase records made by the user
     */
    override fun viewPurchaseHistory(): List<PurchaseRecord> = purchaseHistory

    // Abstract methods to be implemented by subclasses based on their roles

    /**
     * Displays the profile of the user.
     */
    abstract override fun viewProfile()

    /**
     * Views available products in the store.
     * @return A list of available products
     */
    abstract override fun viewProducts(): List<Product>

    /**
     * Purchases a product.
     * @param product The product to purchase
     * @param quantity Quantity of the product to purchase
     * @return True if the purchase is successful, otherwise false
     */
    abstract override fun purchaseProduct(product: Product, quantity: Int): Boolean

    /**
     * Returns a purchased product.
     * @param productId The ID of the product to return
     * @return True if the return is successful, otherwise false
     */
    abstract override fun returnProduct(productId: Int): Boolean
}
