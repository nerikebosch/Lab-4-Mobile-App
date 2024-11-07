package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus


/**
 * Represents a manager of the store, responsible for managing products and users.
 * @param id Unique identifier of the store manager
 * @param name Name of the store manager
 */
abstract class StoreManager(
    val id: Int,
    val name: String
): Worker {

    /**
     * Displays the profile of the store manager.
     */
    override fun viewProfile() {
        println("Manager: $name - ID: $id")
    }

    /**
     * Adds a product to the store.
     * @param product The product to add
     * @param store The store where the product will be added
     */
    override fun addProduct(product: Product, store: Store) {
        store.addProduct(product)
    }

    /**
     * Removes a product from the store.
     * @param product The product to remove
     * @param store The store where the product will be removed
     */
    override fun removeProduct(product: Product, store: Store){
        store.products.remove(product)
    }

    /**
     * Updates the details of a product in the store.
     * @param productId The ID of the product to update
     * @param newPrice The new price of the product
     * @param newQuantity The new quantity of the product
     * @param store The store where the product is located
     */
    override fun updateProductDetails(productId: Int, newPrice: Double, newQuantity: Int, store: Store){
        store.updateProduct(productId, newPrice, newQuantity)
    }

    /**
     * Changes the status of a product (e.g., available, out of stock).
     * @param product The product whose status will be changed
     * @param store The store where the product is located
     * @param status The new status of the product
     */
    override fun changeProductStatus(product: Product, store: Store, status: ProductStatus){
        product.productStatus = status
    }

    /**
     * Adds a user to the store.
     * @param user The user to be added
     * @param store The store where the user will be added
     */
    fun addUser(user: OnlineUser, store: Store){
        store.users.add(user)
        println("User ${user.name} has been added")
    }

    /**
     * Removes a user from the store.
     * @param user The user to be removed
     * @param store The store where the user will be removed from
     */
    fun removeUser(user: OnlineUser, store: Store) {
        store.users.remove(user)
        println("User ${user.name} has been removed")
    }
}