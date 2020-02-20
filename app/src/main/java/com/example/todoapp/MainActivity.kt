package com.example.todoapp


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


const val RESULT_CODE = 1

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdaptaer


    var todos = ArrayList<AllTodo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchJSON()
        todos.add(AllTodo("cook", false))
        todos.add(AllTodo("dance", false))
        todos.add(AllTodo("run", false))
        todos.add(AllTodo("play", false))
        todoAdapter = TodoAdaptaer(this, todos)
        recyc.adapter = todoAdapter

        fab.setOnClickListener {
            var intent = Intent(this, AddTodoActivity::class.java)
            startActivityForResult(intent, RESULT_CODE)
        }

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "resume", Toast.LENGTH_LONG).show()
        val f = getFileStreamPath("myfile")
        if (f.length() != 0L) {
            // empty or doesn't exist
            this.openFileInput("myfile").bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    Log.e("readfile", text)
                    Log.e("readfi", some)
                    todos.add(AllTodo("$some\n$text", false))
                    Log.e("readfile", todos.toString())
                    todoAdapter = TodoAdaptaer(this, todos)
                    recyc.adapter = todoAdapter
                    "$some\n$text"
                }

            }
        }
    }

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                var result = data?.getStringExtra("todolist")
                Log.e("Finishedddd", result.toString())
                val todo = AllTodo(result, false)
//                todo.todoss = result
                todos.add(todo)
                todoAdapter.notifyDataSetChanged()
            }
        }
    }

    fun fetchJSON() {
        val url =
            "https://content.guardianapis.com/search?api-key=cf4f8f5d-9f48-473c-9bc7-cd0c68660616"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execut")
            }
        })
    }

//    fun load(){
//        var filename = Toooo().FILE_NAME
//        val bufferedReader: BufferedReader = File(filename).bufferedReader()
//        val inputString = bufferedReader.use { it.readText() }
//        println(inputString)
//
//       // val todo = AllTodo(sb.toString(),false)
//    }
}