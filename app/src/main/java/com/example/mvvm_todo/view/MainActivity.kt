package com.example.mvvm_todo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_todo.R
import com.example.mvvm_todo.databinding.ActivityMainBinding
import com.example.mvvm_todo.model.TodoModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mTodoListAdapter: TodoListAdpater
    private lateinit var viewDataBinding : ActivityMainBinding
    private val mTodoItems : ArrayList<TodoModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        initAddButton()
    }

    private fun initRecyclerView() {
        mTodoListAdapter = TodoListAdpater(mTodoItems)
        viewDataBinding.recyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mTodoListAdapter
        }
    }

    private fun initAddButton() {
        viewDataBinding.btnAdd.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_todo, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("추가하기")
            .setView(dialogView)
            .setPositiveButton("확인", {dialogInterface, i ->
                val title = dialogView.findViewById<EditText>(R.id.edit_title).text.toString()
                val description = dialogView.findViewById<EditText>(R.id.edit_description).text.toString()
                val createdDate = Date().time

                val todoModel = TodoModel(title, description, createdDate)
                mTodoListAdapter.addItem(todoModel)
                mTodoListAdapter.notifyDataSetChanged()
            })
            .setNegativeButton("취소", null)
            .create()
        dialog.show()
    }
}