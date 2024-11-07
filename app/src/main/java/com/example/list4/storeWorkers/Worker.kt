package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus

/**
 * Interface defining the operations available to a worker in the store (Staff or Manager).
 */
interface Worker {

    /**
     * Displays the profile of the worker.
     */
    fun viewProfile()

    /**
     * Adds a product to the store.
     * @param product The product to be added
     * @param store The store where the product will be added
     */
    fun addProduct(product: Product, store: Store)

    /**
     * Removes a product from the store.
     * @param product The product to be removed
     * @param store The store where the product will be removed
     */
    fun removeProduct(product: Product, store: Store)

    /**
     * Updates the details of a product in the store.
     * @param productId The ID of the product to update
     * @param newPrice The new price of the product
     * @param newQuantity The new quantity of the product
     * @param store The store where the product is located
     */
    fun updateProductDetails(productId: Int, newPrice: Double, newQuantity: Int, store: Store)

    /**
     * Changes the status of a product in the store.
     * @param product The product whose status will be changed
     * @param store The store where the product is located
     * @param status The new status of the product
     */
    fun changeProductStatus(product: Product, store: Store, status: ProductStatus)
    //fun viewAllProduct(): List<Product>
    //fun viewAvailableProduct(): List<Product>
}