package com.example.list4.storeProducts

class Laptop(
    override val id: Int, override val item: String, override var price: Double,
    override var productStatus: ProductStatus, override var quantity: Int, val GPU: String, val CPU : String, val storage: Int, val ram: String, val extra: String
) : Product(){

}