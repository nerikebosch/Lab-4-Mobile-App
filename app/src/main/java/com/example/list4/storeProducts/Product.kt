package com.example.list4.storeProducts
/*
    Abstract class Product that present product in online store has attributes:
    - Id
    - item (name)
    - price
    - quantity -> stockQuantity
    - product status
    implement to sub-class:
    + Laptop
    + Phone
    + Accessory
 */
abstract class Product {
    abstract val id : Int
    abstract val item : String
    abstract var price : Double
    abstract var stockQuantity : Int
    abstract var productStatus : ProductStatus

    // List of products have been purchased
    val purchaseHistory = mutableListOf<PurchaseRecord>()

    // purchase a product
    open fun purchase(userId: Int, productId: Int, quantity: Int, purchaseDate: String): Boolean {
        val stockLeft = stockQuantity - quantity // check how many left in the stock
        return if (productStatus == ProductStatus.AVAILABLE && stockLeft > 0) {
            stockQuantity = stockLeft
            val record = PurchaseRecord(
                userId = userId,
                productId = productId,
                quantity = quantity,
                totalPrice = price * quantity,
                purchaseDate = purchaseDate,
                returnDate = null
            )
            purchaseHistory.add(record)
            true
        } else if (productStatus == ProductStatus.AVAILABLE && stockLeft == 0) {
            stockQuantity = stockLeft // = 0
            productStatus = ProductStatus.OUT_OF_STOCK
            val record = PurchaseRecord(
                userId = userId,
                productId = productId,
                quantity = quantity,
                totalPrice = price * quantity,
                purchaseDate = purchaseDate,
                returnDate = null
            )
            purchaseHistory.add(record)
            println("Item $productId is now out of stock!")
            true
        } else {
            println("Item $productId is out of stock or not available!!")
            false
        }
    }

    // return a product
    open fun returnProduct(userId: Int, productId: Int, returnDate: String): Boolean {
        // Return the record that being return or false if no such record exists.
        val purchasedRecord = purchaseHistory.lastOrNull {it.userId == userId && it.productId == productId}
        return if(purchasedRecord != null) {
            if (productStatus == ProductStatus.AVAILABLE) {
                stockQuantity +=1
            } else if (productStatus == ProductStatus.OUT_OF_STOCK){
                productStatus = ProductStatus.AVAILABLE
                stockQuantity = 1
            }
            true
        } else {
            println("Did not found $item bought by user: $userId")
            false
        }
    }

    // print the purchase record history
    open fun listPurchaseHistory() {
        purchaseHistory.forEach { record ->
            println("User ID: ${record.userId}, " +
                    "Item: ${record.productId}, " +
                    "Purchase date: ${record.purchaseDate}, " +
                    "Return date: ${record.returnDate}")
        }
    }


}