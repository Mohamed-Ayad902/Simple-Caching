package com.example.simplecaching.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecaching.databinding.ItemPostBinding
import com.example.simplecaching.domain.models.Post

class PostsAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<PostsAdapter.PostsVH>() {

    inner class PostsVH(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    private val callBack = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostsVH(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostsVH, position: Int) {
        val post = differ.currentList[position]

        holder.binding.apply {
            tvTitle.text = post.title
            tvBody.text = post.body

            holder.itemView.setOnClickListener { listener.onClick(post) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnClickListener {
        fun onClick(post: Post)
    }

}