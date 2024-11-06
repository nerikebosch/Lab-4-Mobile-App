package com.example.list4.storeWorkers

import com.example.list4.store.Store


class Admin(
    id: Int,
    name: String,
    val email: String = ""
) : StoreManager(id, name) {

    override fun viewProfile() {
        println("Admin Profile - ID: $id, Name: $name, Email: $email")
    }

//    fun removeUserById(userId : Int) {
//
//    }

    fun addStaff(staff: StoreManager, store: Store) {
        store.workers.add(staff)
        println("$name (Administrator) added worker ${staff.name} to the store")
    }

    fun removeStaff(staff: StoreManager, store: Store) {
        store.workers.remove(staff)
        println(println("$name (Administrator) removed worker ${staff.name} from the store"))
    }
}
