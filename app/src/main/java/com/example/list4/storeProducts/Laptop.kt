package com.example.list4.storeProducts

class Laptop(
    override val id: Int,
    override val item: String,
    override var price: Double,
    override var productStatus: ProductStatus,
    override var stockQuantity: Int,
    val cpu: String, val gpu: String, val storage: Int, val ram: String, val extra: String
) : Product(){

    // purchase a product
    override fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val message = super.purchase(userId, productId, quantity, purchaseDate)
        if (message){
            println("Laptop $item bought by user $userId purchased successful")
        } else println("Laptop $item bought by user $userId NOT purchased successful")
        return message
    }

    // return a product
    override fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val message = super.returnProduct(userId, productId, returnDate)
        if (message){
            println("User $userId returned the laptop $item")
        } else println("User $userId returned laptop $item NOT successful!!")
        return message
    }
}