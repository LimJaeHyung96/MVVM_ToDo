package com.example.mvvm_todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_todo.R
import com.example.mvvm_todo.model.TodoModel
import java.text.SimpleDateFormat
import java.util.*

class TodoListAdpater(private val todoItems : ArrayList<TodoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //item_todo를 RecyclerView에 인플레이트하고 이를 전달하여 뷰홀더를 만들고 리턴
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val viewHolder = TodoViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]

        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todoModel)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    fun addItem(todoModel: TodoModel) {
        todoItems.add(todoModel)
    }
}

class TodoViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val title = view.findViewById<TextView>(R.id.tv_title)
    val description = view.findViewById<TextView>(R.id.tv_description)
    val createdDate = view.findViewById<TextView>(R.id.tv_date)

    fun bind(todoModel: TodoModel){
        title.text = todoModel.title
        description.text = todoModel.description
        createdDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
    }

    private fun Long.toDateString(format : String) : String{
        val  simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(Date(this))
    }
}