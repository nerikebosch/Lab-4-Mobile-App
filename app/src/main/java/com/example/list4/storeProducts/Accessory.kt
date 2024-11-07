package com.example.list4.storeProducts


/**
 * Represents an accessory product in the store.
 * @property id Unique identifier of the accessory
 * @property item Name of the accessory
 * @property price Price of the accessory
 * @property stockQuantity Quantity of the accessory in stock
 * @property productStatus Availability status of the accessory
 * @property type The type/category of the accessory (e.g., case, charger, etc.)
 * @property extra Additional details related to the accessory
 */
class Accessory (
    override val id: Int,
    override val item: String,
    override var price: Double,
    override var productStatus: ProductStatus,
    override var stockQuantity: Int,
    val type: String,
    val extra: String = ""
) : Product(){

    // purchase a product
    /**
     * Purchases an accessory product from the store, updating stock and purchase history.
     * @param userId The ID of the user purchasing the accessory
     * @param productId The ID of the accessory being purchased
     * @param quantity The quantity of the accessory being purchased
     * @param purchaseDate The date the purchase is made
     * @return True if the purchase was successful, otherwise false
     */
    override fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val message = super.purchase(userId, productId, quantity, purchaseDate)
        if (message){
            println("Accessory $item bought by user $userId purchased successful")
        } else println("Accessory $item bought by user $userId NOT purchased successful")
        return message
    }

    // return a product
    /**
     * Returns an accessory product to the store, updating stock and purchase history.
     * @param userId The ID of the user returning the accessory
     * @param productId The ID of the accessory being returned
     * @param returnDate The date the accessory is being returned
     * @return True if the return was successful, otherwise false
     */
    override fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val message = super.returnProduct(userId, productId, returnDate)
        if (message){
            println("User $userId returned the accessory $item")
        } else println("User $userId returned accessory $item NOT successful!!")
        return message
    }
}