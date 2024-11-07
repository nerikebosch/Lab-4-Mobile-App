package com.example.list4.storeProducts

/**
 * Represents a laptop product in the store.
 * @property id Unique identifier of the laptop
 * @property item Name of the laptop
 * @property price Price of the laptop
 * @property productStatus Availability status of the laptop
 * @property stockQuantity Quantity of the laptop in stock
 * @property cpu The CPU specification of the laptop
 * @property gpu The GPU specification of the laptop
 * @property storage The storage capacity of the laptop
 * @property ram The RAM specification of the laptop
 * @property extra Additional features of the laptop (optional)
 */
class Laptop(
    override val id: Int,
    override val item: String,
    override var price: Double,
    override var productStatus: ProductStatus,
    override var stockQuantity: Int,
    val cpu: String, val gpu: String, val storage: String, val ram: String, val extra: String = ""
) : Product(){

    // purchase a product
    /**
     * Purchases a laptop product from the store, reducing the stock quantity.
     * @param userId The ID of the user making the purchase
     * @param productId The ID of the laptop being purchased
     * @param quantity The number of laptops to purchase
     * @param purchaseDate The date of the purchase
     * @return True if the purchase was successful, otherwise false
     */
    override fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val message = super.purchase(userId, productId, quantity, purchaseDate)
        if (message){
            println("Laptop $item bought by user $userId purchased successful")
        } else println("Laptop $item bought by user $userId NOT purchased successful")
        return message
    }

    // return a product
    /**
     * Returns a laptop product to the store, updating stock and purchase history.
     * @param userId The ID of the user returning the laptop
     * @param productId The ID of the laptop being returned
     * @param returnDate The date the laptop is being returned
     * @return True if the return was successful, otherwise false
     */
    override fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        val message = super.returnProduct(userId, productId, returnDate)
        if (message){
            println("User $userId returned the laptop $item")
        } else println("User $userId returned laptop $item NOT successful!!")
        return message
    }
}