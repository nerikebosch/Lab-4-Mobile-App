package com.example.list4.storeWorkers

import com.example.list4.store.Store
import com.example.list4.storeProducts.Product
import com.example.list4.storeProducts.ProductStatus

class Staff(
    id: Int,
    name: String,
    val email: String = ""
) : StoreManager(id, name){

    override fun viewProfile() {
        println("Staff profile - ID: $id, Name: $name, Email: $email")
    }

    override fun addProduct(product: Product, store: Store) {
        super.addProduct(product, store)
        println("(Staff) $name added ${product.item} to the store")
    }

    override fun removeProduct(product: Product, store: Store) {
        super.removeProduct(product, store)
        println("(Staff) $name removed ${product.item} from the store")
    }

    override fun changeProductStatus(product: Product, store: Store, status: ProductStatus) {
        super.changeProductStatus(product, store, status)
        println("(Staff) $name changed ${product.item} to status ${product.productStatus}")
    }

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