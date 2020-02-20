package com.example.todoapp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.todo_add.*
import java.io.File


class AddTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_add)
    }

    fun save(v: View) {
        val file = File(this.filesDir, "myfile")
        val text = editTexttod.text.toString()
        this.openFileOutput("myfile", Context.MODE_PRIVATE).use {
            it.write(text.toByteArray())
        }
        finish()
    }
}


