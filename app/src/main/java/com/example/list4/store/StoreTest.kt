package com.example.list4.store

import com.example.list4.storeProducts.Accessory
import com.example.list4.storeProducts.Laptop
import com.example.list4.storeProducts.Phone
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.Customer
import com.example.list4.storeWorkers.Staff
import java.io.File

fun main() {
    val storeTest = StoreTest()

    // Load products from file
    val laptops = storeTest.loadLaptops("app/src/main/java/com/example/list4/store/Laptops.txt")
    val phones = storeTest.loadPhones("app/src/main/java/com/example/list4/store/Phones.txt")
    val accessories = storeTest.loadAccessories("app/src/main/java/com/example/list4/store/Accessories.txt")

    val store = Store()

    laptops.forEach { store.addProduct(it) }
    phones.forEach { store.addProduct(it) }
    accessories.forEach { store.addProduct(it) }
    println(" ------------------ ")

    println("\tList all products")
    store.listAllProducts()
    println(" ------------------ ")

    println("\tCreate store manager and admin")
    val admin = Admin(
        id = 0,
        name = "Nerike Bosch",
        email = "nerike.b@gmail.com"
    )
    val staff = Staff(
        id = 1,
        name = "Quang Pham",
        email = "quangptt0910@gmail.com"
    )
    admin.addStaff(staff, store)
    store.listAllWorkers()
    println(" ------------------ ")

    println("\tAdd some users/ customers")
    val customer1 = Customer(
        id = 100,
        name = "mrA",
        email = "AAhahaayy@gmail.com",
        address = "Wroclaw"
    )
    val customer2 = Customer(
        id = 102,
        name = "online-register",
        email = "Auto_generated-email@gmail.com",
        address = "someWhere"
    )
    // customer added by staff
    staff.addUser(customer1, store)
    // customer self register to the store
    store.registerUser(customer2)
    store.listAllUsers()
    println(" ------------------ ")

    println("\tCustomers buy something")
    val product1 = store.findProductById(200)
    if (product1 != null) {
        customer1.purchaseProduct(product1, 1)
    }

}

class StoreTest {

    fun loadLaptops(fileName: String): MutableList<Laptop> {
        val laptops = mutableListOf<Laptop>()
        File(fileName).forEachLine { line ->
            val parts = line.split(",")
            val id = parts[0].toInt()
            val item = parts[1]
            val price = parts[2].toDouble()
            val productStatus = ProductStatus.valueOf(parts[3])
            val stockQuantity = parts[4].toInt()
            val cpu = parts[5]
            val gpu = parts[6]
            val storage = parts[7]
            val ram = parts[8]
            val extra = parts[9]
            laptops.add(Laptop(id, item, price, productStatus, stockQuantity, cpu, gpu, storage, ram, extra))
        }
        return laptops
    }

    fun loadPhones(fileName: String): MutableList<Phone> {
        val phones = mutableListOf<Phone>()
        File(fileName).forEachLine { line ->
            val parts = line.split(",")
            val id = parts[0].toInt()
            val item = parts[1]
            val price = parts[2].toDouble()
            val productStatus = ProductStatus.valueOf(parts[3])
            val stockQuantity = parts[4].toInt()
            val storage = parts[5]
            val cameraQuality = parts[6]
            val extra = parts[7]
            phones.add(Phone(id, item, price, productStatus, stockQuantity, storage, cameraQuality, extra))
        }
        return phones
    }

    fun loadAccessories(fileName: String): MutableList<Accessory> {
        val accessories = mutableListOf<Accessory>()
        File(fileName).forEachLine { line ->
            val parts = line.split(",")
            val id = parts[0].toInt()
            val item = parts[1]
            val price = parts[2].toDouble()
            val productStatus = ProductStatus.valueOf(parts[3])
            val stockQuantity = parts[4].toInt()
            val type = parts[5]
            val extra = parts[6]
            accessories.add(Accessory(id, item, price, productStatus, stockQuantity, type, extra))
        }
        return accessories
    }
}
