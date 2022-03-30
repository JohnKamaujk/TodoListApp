package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todos:MutableList<Todo>):RecyclerView.Adapter<TodoAdapter.TodoViewholder>() {


    class TodoViewholder(itemview:View):RecyclerView.ViewHolder(itemview){
        val tvTodoTitle:TextView=itemview.findViewById(R.id.tvTodoTitle)
        val cbDone:CheckBox=itemview.findViewById(R.id.cbDone)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewholder {
       return TodoViewholder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_todo,
               parent,
               false
           )
       )


    }
    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteDoneTodos(){
       todos.removeAll {todo ->
           todo.ischecked
       }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle:TextView,ischecked:Boolean){
        if (ischecked){
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }


    }

    override fun onBindViewHolder(holder: TodoViewholder, position: Int) {
        val curTodo = todos[position]

        holder.apply {
            tvTodoTitle.text=curTodo.title
            cbDone.isChecked=curTodo.ischecked
            toggleStrikeThrough(tvTodoTitle,curTodo.ischecked)
            cbDone.setOnCheckedChangeListener { _,isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.ischecked=!curTodo.ischecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}