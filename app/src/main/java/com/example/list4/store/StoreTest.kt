package com.example.list4.store

import com.example.list4.storeProducts.Accessory
import com.example.list4.storeProducts.Laptop
import com.example.list4.storeProducts.Phone
import com.example.list4.storeProducts.ProductStatus
import com.example.list4.storeWorkers.Admin
import com.example.list4.storeWorkers.Customer
import com.example.list4.storeWorkers.Staff
import java.io.File

/**
 * Main function for testing store functionalities.
 *
 * This function is responsible for testing various functionalities of the store,
 * such as adding products, registering users, and making purchases. It also tests
 * the purchasing and returning mechanisms, as well as adding and removing users
 * and staff. Additionally, it verifies the stock management and product status
 * updates.
 *
 * The following steps are performed:
 * 1. Loading products (Laptops, Phones, and Accessories) from external files.
 * 2. Adding products to the store and listing all products.
 * 3. Creating and adding an Admin and Staff members.
 * 4. Adding and registering users (customers) to the store.
 * 5. Testing the purchase functionality for customers.
 * 6. Testing the return functionality for products.
 * 7. Listing all users and products after operations are performed.
 *
 * The test cases ensure proper behavior of product management, user registration,
 * and purchase/return logic in the store system.
 */
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
        address = "Wroclaw",
        store = store
    )
    val customer2 = Customer(
        id = 102,
        name = "online-register",
        email = "Auto_generated-email@gmail.com",
        address = "someWhere",
        store = store
    )
    // customer added by staff
    staff.addUser(customer1, store)
    // customer self register to the store
    store.registerUser(customer2)
    store.listAllUsers()
    println(" ------------------ ")

    println("\tCustomers buy something")
    customer1.purchaseProductById(200, 1)

    val product2 = store.findProductById(101)
    if (product2 != null) {
        customer2.purchaseProduct(product2, 1)
    }
    println("\nPurchased history:")
    store.listAllPurchasedProducts()

    println(" ------------------ ")
    println("\tTest: Purchasing when stock is low")
    val lowStockProduct = store.findProductById(100) // assuming product ID 100 exists and has low stock
    if (lowStockProduct != null) {
        val success = customer1.purchaseProduct(lowStockProduct, 10) // attempting to purchase more than the available stock
        println("Purchase of product with low stock: $success")
    }

    println(" ------------------ ")
    println("\tTest: Purchasing an unavailable product")
    val unavailableProduct = store.findProductById(999) // assuming product ID 999 does not exist
    if (unavailableProduct != null) {
        val success = customer1.purchaseProduct(unavailableProduct, 1)
        println("Attempted purchase of unavailable product: $success")
    }

    println(" ------------------ ")
    println("\tTest: Purchasing more than the available quantity")
    val productWithLimitedStock = store.findProductById(101) // assuming product ID 101 exists
    if (productWithLimitedStock != null) {
        val availableStock = productWithLimitedStock.stockQuantity
        val success = customer1.purchaseProduct(productWithLimitedStock, availableStock + 1) // attempt to buy more than available
        println("Attempt to purchase more than available stock: $success")
    }

    println(" ------------------ ")
    println("\tTest: Returning a product successfully")
    val purchasedProduct = store.findProductById(100) // assuming customer1 purchased product with ID 100
    if (purchasedProduct != null) {
        val success = customer1.returnProduct(purchasedProduct.id)
        println("Product return success: $success")
    }

    println(" ------------------ ")
    println("\tTest: Returning a product not purchased")
    val nonPurchasedProduct = store.findProductById(102) // assuming customer1 did not buy product with ID 102
    if (nonPurchasedProduct != null) {
        val success = customer1.returnProduct(nonPurchasedProduct.id)
        println("Attempt to return non-purchased product: $success")
    }

    println(" ------------------ ")
    println("\tTest: Admin adding and removing staff")
    val newStaff = Staff(id = 3, name = "John Doe")
    admin.addStaff(newStaff, store) // Admin adds a new staff
    println("Staff list after addition:")
    store.listAllWorkers()

    admin.removeStaff(newStaff, store) // Admin removes the staff
    println("Staff list after removal:")
    store.listAllWorkers()

    println(" ------------------ ")
    println("\tTest: Staff updating product status")
    val productToUpdate = store.findProductById(101) // assuming product with ID 101 exists
    if (productToUpdate != null) {
        staff.changeProductStatus(productToUpdate, store, ProductStatus.OUT_OF_STOCK) // Changing product status
        println("Product status after change: ${productToUpdate.productStatus}")
    }

    println(" ------------------ ")
    println("\tTest: Invalid user registration with same email")
    val duplicateEmailCustomer = Customer(
        id = 105,
        name = "Duplicate",
        email = "AAhahaayy@gmail.com", // same email as customer1
        address = "New York",
        store = store
    )
    store.registerUser(duplicateEmailCustomer) // This should handle email duplication

    println(" ------------------ ")
    println("\tTest: Registering user with invalid email format")
    val invalidEmailCustomer = Customer(
        id = 106,
        name = "Invalid Email",
        email = "invalid-email", // invalid email format
        address = "California",
        store = store
    )
    store.registerUser(invalidEmailCustomer) // This should fail due to invalid email

    println(" ------------------ ")
    println("\tTest: List all products after adding new ones")
    val newPhone = Phone(
        id = 201,
        item = "New Phone Model",
        price = 799.99,
        productStatus = ProductStatus.AVAILABLE,
        stockQuantity = 50,
        storage = "128GB",
        cameraQuality = "12MP"
    )
    store.addProduct(newPhone)
    store.listAllProducts() // This should list all products including the new one

    println(" ------------------ ")
    println("\tTest: List all users after adding customers")
    store.listAllUsers() // This should list all registered users

    println(" ------------------ ")
    println("\tTest: List all purchase histories")
    store.listAllPurchasedProducts() // This should show the purchase history for the users

    println(" ------------------ ")
    println("\tTest: Purchasing with incorrect product ID")
    val nonExistentProduct = store.findProductById(9999) // product ID that doesn't exist
    if (nonExistentProduct != null) {
        val success = customer1.purchaseProduct(nonExistentProduct, 1)
        println("Attempt to purchase with incorrect product ID: $success")
    }


}

/**
 * A helper class for loading and handling product data from external files.
 *
 * This class provides methods to load lists of products (Laptops, Phones, and Accessories)
 * from files, and convert the file data into appropriate product objects to be added to
 * the store. It supports the reading of file data and parsing it into product properties.
 *
 * The following methods are provided:
 * 1. `loadLaptops(fileName: String)`: Loads laptop data from the provided file and returns a list of `Laptop` objects.
 * 2. `loadPhones(fileName: String)`: Loads phone data from the provided file and returns a list of `Phone` objects.
 * 3. `loadAccessories(fileName: String)`: Loads accessory data from the provided file and returns a list of `Accessory` objects.
 *
 * Each of these methods reads data from a file, parses it into individual product properties,
 * and returns a list of corresponding products to be added to the store's inventory.
 */
class StoreTest {


    /**
     * Loads a list of laptop products from a specified file.
     *
     * The file should contain a comma-separated list of product properties for each laptop,
     * including product ID, item name, price, stock quantity, CPU, GPU, storage, RAM, and extra features.
     * This method parses the data and returns a list of `Laptop` objects.
     *
     * @param fileName The path to the file containing the laptop data.
     * @return A list of `Laptop` objects parsed from the file.
     */
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

    /**
     * Loads a list of phone products from a specified file.
     *
     * The file should contain a comma-separated list of product properties for each phone,
     * including product ID, item name, price, stock quantity, storage, camera quality, and extra features.
     * This method parses the data and returns a list of `Phone` objects.
     *
     * @param fileName The path to the file containing the phone data.
     * @return A list of `Phone` objects parsed from the file.
     */
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

    /**
     * Loads a list of accessory products from a specified file.
     *
     * The file should contain a comma-separated list of product properties for each accessory,
     * including product ID, item name, price, stock quantity, type, and extra features.
     * This method parses the data and returns a list of `Accessory` objects.
     *
     * @param fileName The path to the file containing the accessory data.
     * @return A list of `Accessory` objects parsed from the file.
     */
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
