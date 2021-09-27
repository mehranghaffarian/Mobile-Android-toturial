package com.example.kotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainListView = findViewById<ListView>(R.id.mainListView)

        val users = ArrayList<User>()
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("mehran ghaffarian", "now", 123))
        users.add(User("kian ghaffarian", "today", 124))
        users.add(User("kian ghaffarian", "today", 124))
        users.add(User("kian ghaffarian", "today", 124))
        users.add(User("kian ghaffarian", "today", 124))
        users.add(User("kian ghaffarian", "today", 124))
        users.add(User("kian ghaffarian", "today", 124))

        mainListView.adapter = CustomAdaptor(this, users)
    }
}