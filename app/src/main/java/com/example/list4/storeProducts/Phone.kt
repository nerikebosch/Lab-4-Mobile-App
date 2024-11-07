package com.example.list4.storeProducts


/**
 * Represents a phone product in the store.
 * @property id Unique identifier of the phone
 * @property item Name of the phone
 * @property price Price of the phone
 * @property productStatus Availability status of the phone
 * @property stockQuantity Quantity of the phone in stock
 * @property storage Storage capacity of the phone
 * @property cameraQuality Camera quality of the phone
 * @property extra Additional features of the phone (optional)
 */
class Phone(
    override val id: Int,
    override val item: String,
    override var price: Double,
    override var productStatus: ProductStatus,
    override var stockQuantity: Int,
    val storage: String, val cameraQuality: String, val extra: String = ""
) : Product() {

    // purchase a product
    /**
     * Purchases a phone product from the store, reducing the stock quantity.
     * @param userId The ID of the user making the purchase
     * @param productId The ID of the phone being purchased
     * @param quantity The number of phones to purchase
     * @param purchaseDate The date of the purchase
     * @return True if the purchase was successful, otherwise false
     */
    override open fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val message = super.purchase(userId, productId, quantity, purchaseDate)
        if (message){
            println("Phone $item bought by user with id $userId purchased successful")
        } else println("Phone $item bought by user with id $userId NOT purchased successful")
        return message
    }

    // return a product
    /**
     * Returns a phone product to the store, updating stock and purchase history.
     * @param userId The ID of the user returning the phone
     * @param productId The ID of the phone being returned
     * @param returnDate The date the phone is being returned
     * @return True if the return was successful, otherwise false
     */
    override open fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val message = super.returnProduct(userId, productId, returnDate)
        if (message){
            println("User $userId returned the phone $item")
        } else println("User $userId returned phone $item NOT successful!!")
        return message
    }

}