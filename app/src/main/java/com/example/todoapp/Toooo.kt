package com.example.todoapp

import android.content.Context
import java.io.File

class Toooo(context: Context) {
    //val FILE_NAME = "example.txt"
    val file = File(context.filesDir, "myfile")
}