package com.example.list4.storeProducts


/**
 * Represents a record of a purchase made by a user, storing information about the transaction.
 * @property userId ID of the user making the purchase
 * @property productId ID of the product purchased
 * @property quantity Quantity of product purchased
 * @property totalPrice Total price of the purchase
 * @property purchaseDate Date of the purchase
 * @property returnDate Date when the product was returned, if applicable
 */
data class PurchaseRecord(
    val userId: Int,
    val productId: Int,
    val quantity: Int,
    val totalPrice: Double,
    val purchaseDate: String,
    var returnDate: String?
)
