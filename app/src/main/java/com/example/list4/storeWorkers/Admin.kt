package com.example.list4.storeWorkers

import com.example.list4.store.Store


/**
 * Represents an admin user who manages other staff and store operations.
 * @param id Unique identifier of the admin
 * @param name Name of the admin
 * @param email Email of the admin
 */
class Admin(
    id: Int,
    name: String,
    val email: String = ""
) : StoreManager(id, name) {

    /**
     * Displays the admin's profile information.
     */
    override fun viewProfile() {
        println("Admin Profile - ID: $id, Name: $name, Email: $email")
    }

//    fun removeUserById(userId : Int) {
//
//    }

    /**
     * Adds a staff member to the store's workforce.
     * @param staff The staff member to be added
     * @param store The store where the staff member will be added
     */
    fun addStaff(staff: StoreManager, store: Store) {
        store.workers.add(staff)
        println("$name (Administrator) added worker ${staff.name} to the store")
    }

    /**
     * Removes a staff member from the store's workforce.
     * @param staff The staff member to be removed
     * @param store The store where the staff member will be removed from
     */
    fun removeStaff(staff: StoreManager, store: Store) {
        store.workers.remove(staff)
        println(println("$name (Administrator) removed worker ${staff.name} from the store"))
    }
}
