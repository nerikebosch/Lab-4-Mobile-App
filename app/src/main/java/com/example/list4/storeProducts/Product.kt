package com.example.list4.storeProducts

abstract class Product {
    abstract val id : Int
    abstract val item : String
    abstract var price : Double
    abstract var quantity : Int
    abstract var productStatus : ProductStatus

    val purchaseHistory = mutableListOf<PurchaseRecord>()
}