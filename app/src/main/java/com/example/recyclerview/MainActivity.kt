package com.example.recyclerview

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var adapter: RecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var recyclerDataClass: ArrayList<RecyclerDataClass> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)
        floatingActionButton = findViewById(R.id.fab)
        adapter = RecyclerAdapter(recyclerDataClass)
        linearLayoutManager =LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.layout_dialog)
            val customView: View = layoutInflater.inflate(R.layout.layout_dialog, null)
            dialog.setContentView(customView)
            dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            val etTitle = dialog.findViewById(R.id.etTitle) as EditText
            val etMessage = dialog.findViewById(R.id.etMessage) as EditText
            val btnAddValue = dialog.findViewById(R.id.btnAddValue) as Button
            btnAddValue.setOnClickListener {
                if(etTitle.text.toString().isNullOrEmpty()){
                    etTitle.error = resources.getString(R.string.add_title)
                    return@setOnClickListener
                }
                if(etMessage.text.toString().isNullOrEmpty()){
                    etMessage.error = resources.getString(R.string.add_message)
                    return@setOnClickListener
                }else{
                    recyclerDataClass.add(RecyclerDataClass(etTitle.text.toString(),etMessage.text.toString()))
                    adapter.notifyDataSetChanged()
                }
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}