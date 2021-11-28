package com.example.todowithretrofit.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todowithretrofit.RetrofitApi.TodoApiData
import com.example.todowithretrofit.databinding.ItemTodoBinding

class TodoRecyclerViewAdapter  : RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>(){
    inner class TodoViewHolder(val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root)

 private val diffCallback = object :DiffUtil.ItemCallback<TodoApiData>(){
     override fun areItemsTheSame(oldItem: TodoApiData, newItem: TodoApiData): Boolean {
return oldItem.id==newItem.id
     }

     override fun areContentsTheSame(oldItem: TodoApiData, newItem: TodoApiData): Boolean {
return oldItem==newItem
     }
 }
private  val differ =AsyncListDiffer(this,diffCallback)
    var allTodos: List<TodoApiData>
    get()= differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
return TodoViewHolder(ItemTodoBinding.inflate(
    LayoutInflater.from(parent.context),
    parent,
    false

))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = allTodos[position  ]
            tvTitle.text = todo.title
            cbDone.isChecked=todo.completed
        }
    }

    override fun getItemCount(): Int {
        return allTodos.size
    }


}