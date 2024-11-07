package com.example.list4.storeProducts

class Phone(
    override val id: Int,
    override val item: String,
    override var price: Double,
    override var productStatus: ProductStatus,
    override var stockQuantity: Int,
    val storage: String, val cameraQuality: String, val extra: String = ""
) : Product() {

    // purchase a product
    override open fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val message = super.purchase(userId, productId, quantity, purchaseDate)
        if (message){
            println("Phone $item bought by user with id $userId purchased successful")
        } else println("Phone $item bought by user with id $userId NOT purchased successful")
        return message
    }

    // return a product
    override open fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val message = super.returnProduct(userId, productId, returnDate)
        if (message){
            println("User $userId returned the phone $item")
        } else println("User $userId returned phone $item NOT successful!!")
        return message
    }

}