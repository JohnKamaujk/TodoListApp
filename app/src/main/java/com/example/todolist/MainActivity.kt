package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var btnAddTodo:Button
    private lateinit var btnDeleteDoneTodos:Button
    private lateinit var rvTodoItems:RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var etTodoTitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTodo=findViewById(R.id.btnAddTodo)
        btnDeleteDoneTodos=findViewById(R.id.btnDeleteDoneTodos)
        etTodoTitle=findViewById(R.id.etTodoTitle)
        rvTodoItems=findViewById(R.id.rvTodoItems)
        todoAdapter= TodoAdapter(mutableListOf())

        rvTodoItems.adapter=todoAdapter
        rvTodoItems.layoutManager=LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle=etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo=Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
           todoAdapter.deleteDoneTodos()
        }

    }
}