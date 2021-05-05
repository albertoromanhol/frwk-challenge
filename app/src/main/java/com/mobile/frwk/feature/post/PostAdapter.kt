package com.mobile.frwk.feature.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.databinding.PostItemBinding

class PostAdapter : ListAdapter<PostEntity, PostAdapter.ViewHolder>(TodoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: PostItemBinding? = null
    ) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: PostEntity) {

            binding?.postItemId?.text = "id: ${item.id}"
            binding?.postItemUserId?.text = "userId: ${item.userId}"
            binding?.postItemTitle?.text = item.title
            binding?.postItemBody?.text = item.body

            binding?.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<PostEntity>() {
    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem == newItem
    }
}
