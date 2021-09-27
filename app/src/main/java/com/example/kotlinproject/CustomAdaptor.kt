package com.example.kotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdaptor(context: Context, users: ArrayList<User>) : BaseAdapter() {
    private val c = context
    private val arr = users

    override fun getCount(): Int {
        return arr.size
    }

    override fun getItem(p0: Int): Any {
        return arr[p0]
    }

    override fun getItemId(p0: Int): Long {
        return arr[p0].getId()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val myView = LayoutInflater.from(c)
        val temp = myView.inflate(R.layout.user_view, p2, false)

        temp.findViewById<TextView>(R.id.userName).text = arr[p0].getName()
        temp.findViewById<TextView>(R.id.timestamp).text = arr[p0].getTime()

        return temp
    }
}