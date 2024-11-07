package com.example.list4.storeWorkers

import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.PurchaseRecord

/**
 * Interface defining the operations available to a user in the store.
 */
interface User {

    /**
     * Displays the profile of the user.
     */
    fun viewProfile()

    /**
     * Views the list of available products in the store.
     * @return A list of products available for purchase
     */
    fun viewProducts(): List<Product>

    /**
     * Views the user's purchase history.
     * @return A list of purchase records associated with the user
     */
    fun viewPurchaseHistory(): List<PurchaseRecord>

    /**
     * Purchases a product from the store.
     * @param product The product to purchase
     * @param quantity Quantity of the product to purchase
     * @return True if the purchase is successful, otherwise false
     */
    fun purchaseProduct(product: Product, quantity: Int): Boolean

    /**
     * Returns a purchased product to the store.
     * @param productId The ID of the product to return
     * @return True if the return is successful, otherwise false
     */
    fun returnProduct(productId: Int): Boolean
}