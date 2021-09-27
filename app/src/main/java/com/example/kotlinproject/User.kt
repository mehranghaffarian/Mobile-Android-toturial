package com.example.kotlinproject

class User(private val name: String, private val time: String, private val id : Long) {
    fun getName(): String {
        return name
    }

    fun getTime(): String {
        return time
    }

    fun getId(): Long {
        return id
    }
}
