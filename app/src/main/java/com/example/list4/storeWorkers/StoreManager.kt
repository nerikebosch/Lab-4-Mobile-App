package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus

abstract class StoreManager(
    val id: Int,
    val name: String
): Worker {

    override fun viewProfile() {
        println("Manager: $name - ID: $id")
    }
    override fun addProduct(product: Product, store: Store) {
        store.addProduct(product)
    }
    override fun removeProduct(product: Product, store: Store){
        store.products.remove(product)
    }
    override fun updateProductDetails(productId: Int, newPrice: Double, newQuantity: Int, store: Store){
        store.updateProduct(productId, newPrice, newQuantity)
    }
    override fun changeProductStatus(product: Product, store: Store, status: ProductStatus){
        product.productStatus = status
    }

    fun addUser(user: OnlineUser, store: Store){
        store.users.add(user)
        println("User ${user.name} has been added")
    }

    fun removeUser(user: OnlineUser, store: Store) {
        store.users.remove(user)
        println("User ${user.name} has been removed")
    }
}