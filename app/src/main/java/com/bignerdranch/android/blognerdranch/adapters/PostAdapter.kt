package com.bignerdranch.android.blognerdranch.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.models.PostMetadata

class PostAdapter(val postMetadata: List<PostMetadata>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun getItemCount(): Int {
        return postMetadata.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postMetadata[position]
        holder.bind(post)
    }
}