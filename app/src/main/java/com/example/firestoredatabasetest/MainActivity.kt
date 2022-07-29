package com.example.firestoredatabasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var adapter: Adapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()

        adapter = Adapter(userArrayList)

        recyclerView.adapter = adapter

        EventChangeListener()

    }


    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("Family").orderBy("firstName", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {

                    if (error != null) {

                        Log.e("Firestore Error", error.message.toString())
                        return

                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {


                        if (dc.type == DocumentChange.Type.ADDED) {

                            userArrayList.add(dc.document.toObject(User::class.java))

                        }
                    }

                    adapter.notifyDataSetChanged()
                }


            })


    }
}