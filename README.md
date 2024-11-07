# Simple Online Management Store
    contributors: Nerike Bosch, Quang Pham
## Main goal
From _Mobile application development 2024/2025_, List 4, by Głąba Paweł

The task is to design and create a program in Kotlin that simulates an Online Store
Management System. You will need to use concepts of classes, objects, inheritance,
interfaces, abstract classes, as well as data and enum classes. The program should allow the
management of products in the store, handle customer and admin functionalities, and track the
purchase history.

    Requirements:
    1. Create an abstract class that represents a product in the online store.
    2. Create classes derived from the abstract product class to describe specific types of products.
    3. Create an interface that defines methods allowing users to make purchases and return products.
    4. Create an abstract class that implements the User interface.
    5. Create classes to describe specific types of users (e.g., customer, admin) that inherit from the abstract User class.
    6. Define an enum class to represent the status of products.
    7. Create a data class to store information about a purchase.
    8. Create a class that manages the store’s products and users.
    9. Create a file to test the functionality of your program

## Package structure
* store
* storeProducts
* storeWorkers

### store

Class **store** has :
* Attributes
    * **products**: list of store's product
    * **users**: list of store's users/customers
    * **workers**: list of store's workers/staffs
    * **purchasedProducts**: list of store's purchased records
* Method/Function
    * add, find, remove, update products
    * list (available) products
    * register, remove users
    * list all users and wokers
    * add a purchased (made by customer)
    * list all purchased records
### storeProducts
To manage and modify the products in the store

Abstract class **Product** has
* Attributes:
    * id
    * item (name of the product)
    * price
    * stockQuantity
    * productStatus (Handle by enum class ProductStatus)
* Subclass product categorize:
    * Laptop
    * Phone
    * Accessory
* enum class Productstatus
    * Available
    * Out of stock
* data class PurchaseRecord

* Method:
    * tracking the purchase history
    * purchase product - record to purchase history
    * return product - record to purchase history
