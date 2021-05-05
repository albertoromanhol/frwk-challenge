package com.mobile.frwk.feature.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.databinding.AlbumItemBinding
import com.mobile.frwk.databinding.TodoItemBinding

class AlbumAdapter : ListAdapter<AlbumEntity, AlbumAdapter.ViewHolder>(TodoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: AlbumItemBinding? = null
    ) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: AlbumEntity) {

            binding?.albumItemId?.text = item.id.toString()
            binding?.albumItemUserId?.text = item.userId.toString()
            binding?.albumItemTitle?.text = item.title

            binding?.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class TodoDiffCallback : DiffUtil.ItemCallback<AlbumEntity>() {
    override fun areItemsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean {
        return oldItem == newItem
    }
}
