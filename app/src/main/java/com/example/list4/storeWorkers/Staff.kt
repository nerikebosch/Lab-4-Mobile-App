package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus

/**
 * Represents a staff member who helps manage the store's operations related to products.
 * @param id Unique identifier of the staff member
 * @param name Name of the staff member
 * @param email Email of the staff member
 */
class Staff(
    id: Int,
    name: String,
    val email: String = ""
) : StoreManager(id, name){

    /**
     * Displays the profile of the staff member.
     */
    override fun viewProfile() {
        println("Staff profile - ID: $id, Name: $name, Email: $email")
    }

    /**
     * Adds a product to the store.
     * @param product The product to be added
     * @param store The store where the product will be added
     */
    override fun addProduct(product: Product, store: Store) {
        super.addProduct(product, store)
        println("(Staff) $name added ${product.item} to the store")
    }

    /**
     * Removes a product from the store.
     * @param product The product to be removed
     * @param store The store where the product will be removed
     */
    override fun removeProduct(product: Product, store: Store) {
        super.removeProduct(product, store)
        println("(Staff) $name removed ${product.item} from the store")
    }

    /**
     * Changes the status of a product in the store.
     * @param product The product whose status will be changed
     * @param store The store where the product is located
     * @param status The new status of the product
     */
    override fun changeProductStatus(product: Product, store: Store, status: ProductStatus) {
        super.changeProductStatus(product, store, status)
        println("(Staff) $name changed ${product.item} to status ${product.productStatus}")
    }

    /**
     * Updates the details of a product in the store.
     * @param productId The ID of the product to update
     * @param newPrice The new price of the product
     * @param newQuantity The new quantity of the product
     * @param store The store where the product is located
     */
    override fun updateProductDetails(
        productId: Int,
        newPrice: Double,
        newQuantity: Int,
        store: Store
    ) {
        super.updateProductDetails(productId, newPrice, newQuantity, store)
        println("(Staff) $name update product ${productId}")
    }

}