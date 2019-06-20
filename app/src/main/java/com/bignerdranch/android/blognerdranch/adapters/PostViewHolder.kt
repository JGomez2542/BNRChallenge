package com.bignerdranch.android.blognerdranch.adapters

import android.provider.SyncStateContract
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bignerdranch.android.blognerdranch.common.BASE_URL
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import com.bignerdranch.android.blognerdranch.ui.post.PostActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_list_item.*

class PostViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), View.OnClickListener,
    LayoutContainer {

    var postMetadata: PostMetadata? = null

    init {
        containerView.setOnClickListener(this)
    }

    fun bind(postMetadata: PostMetadata) {
        this.postMetadata = postMetadata
        textView_title.text = postMetadata.title
        textView_author.text = postMetadata.author?.name
        textView_publishDate.text = postMetadata.publishDate
        textView_summary.text = postMetadata.summary

        val progressDrawable = CircularProgressDrawable(containerView.context)
        progressDrawable.strokeWidth = 5f
        progressDrawable.centerRadius = 30f
        progressDrawable.start()

        val options = RequestOptions()
        options.centerCrop()
        options.placeholder(progressDrawable)

        Glide.with(containerView.context)
            .asBitmap()
            .load("$BASE_URL${postMetadata.author?.imageUrl}")
            .apply(options)
            .into(author_image)
    }

    override fun onClick(v: View) {
        val context = v.context
        context.startActivity(PostActivity.newIntent(v.context, postMetadata!!.postId!!))
    }

}