package com.example.todoapp

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TodoAdaptaer(var context: Context, var arrays: ArrayList<AllTodo>) :
    RecyclerView.Adapter<TodoAdaptaer.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var todoShow: TextView = itemView.findViewById(R.id.alltext)
        var checkShow: CheckBox = itemView.findViewById(R.id.allcheckbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return arrays.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allTodo = arrays[position]
        holder.todoShow.text = allTodo.todoss
        holder.checkShow.isChecked = allTodo.check
        holder.checkShow.setOnClickListener {

            if (holder.checkShow.isChecked) {
                holder.todoShow.paintFlags =
                    holder.todoShow.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//               holder.todoShow.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
//                holder.todoShow.setTextColor(Color.GREEN)
                Toast.makeText(context, "${holder.todoShow.text} checked", Toast.LENGTH_LONG).show()
                Log.e("Message", "True")
            } else if (!holder.checkShow.isChecked) {
                holder.todoShow.paintFlags =
                    holder.todoShow.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                //holder.todoShow.setTextColor(Color.BLACK)
                Toast.makeText(context, "${holder.todoShow.text} Unchecked", Toast.LENGTH_LONG)
                    .show()
            }

        }


    }
}