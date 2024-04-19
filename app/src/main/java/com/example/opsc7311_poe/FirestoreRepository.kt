package com.example.opsc7311_poe

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirestoreRepository {
    private val db = Firebase.firestore

    fun createTables() {
        // Create a collection reference for each table
        val usersCollection = db.collection("users")
        val timeEntriesCollection = db.collection("timeEntries")
        val categoriesCollection = db.collection("categories")

        // Add a document to each collection to create the table
        usersCollection.document("placeholder").set(mapOf("placeholder" to "data"))
        timeEntriesCollection.document("placeholder").set(mapOf("placeholder" to "data"))
        categoriesCollection.document("placeholder").set(mapOf("placeholder" to "data"))
    }

    //Method to check if user already exists in User table
    fun userExists(username: String, callback: (Boolean) -> Unit) {
        val userCollection = db.collection("users")
        userCollection.document(username).get()
            .addOnSuccessListener { document ->
                callback(document.exists())
            }
            .addOnFailureListener { e ->
                // Handle failure
                callback(false)
            }
    }

    //Method to get user from User table
    fun getUser(username: String, callback: (User?) -> Unit) {
        val userCollection = db.collection("users")
        userCollection.document(username).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject(User::class.java)
                    callback(user)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
                callback(null)
            }
    }

    //Method to add user to User table
    fun addUser(user: User, callback: (Boolean) -> Unit) {
        val userCollection = db.collection("users")
        userCollection.document(user.username).set(user)
            .addOnSuccessListener {
                // Handle success
                callback(true)
            }
            .addOnFailureListener { e ->
                // Handle failure
                callback(false)
            }
    }

    fun addTimeEntry(timeEntry: TimeEntry) {
        val timeEntriesCollection = db.collection("timeEntries")
        timeEntriesCollection.document().set(timeEntry)
            .addOnSuccessListener {
                // Handle success
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    fun addCategory(category: Category) {
        val categoriesCollection = db.collection("categories")
        categoriesCollection.document().set(category)
            .addOnSuccessListener {
                // Handle success
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    // Add more methods as needed for read, update, delete operations
}