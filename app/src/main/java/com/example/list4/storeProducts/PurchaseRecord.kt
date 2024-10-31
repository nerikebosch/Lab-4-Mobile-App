package com.example.list4.storeProducts

data class PurchaseRecord(
    val userId: Int,
    val productId: Int,
    val quantity: Int,
    val totalPrice: Double,
    val purchaseDate: String,
    var returnDate: String?
)
