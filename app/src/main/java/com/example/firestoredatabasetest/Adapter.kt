package com.example.firestoredatabasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false
        )

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {

        val user: User = userList[position]
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
        holder.age.text = user.age.toString()


    }

    override fun getItemCount(): Int {

        return userList.size

    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.tvFirstName)
        val lastName: TextView = itemView.findViewById(R.id.tvLastName)
        val age: TextView = itemView.findViewById(R.id.tvAge)


    }
}