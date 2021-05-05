package com.mobile.frwk.feature.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.databinding.TodoItemBinding

class TodoAdapter : ListAdapter<TodoEntity, TodoAdapter.ViewHolder>(TodoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: TodoItemBinding? = null
    ) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: TodoEntity) {

            binding?.todoItemId?.text = item.id.toString()
            binding?.todoItemUserId?.text = item.userId.toString()
            binding?.todoItemTitle?.text = item.title
            binding?.todoItemCompleted?.text = item.completed.toString()

            binding?.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TodoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class TodoDiffCallback : DiffUtil.ItemCallback<TodoEntity>() {
    override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem == newItem
    }
}
