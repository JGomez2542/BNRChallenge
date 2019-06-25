package com.bignerdranch.android.blognerdranch.ui.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bignerdranch.android.blognerdranch.AppController
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.common.EXTRA_POST_ID
import com.bignerdranch.android.blognerdranch.common.whenNull
import com.bignerdranch.android.blognerdranch.di.activity.ActivityModule
import com.bignerdranch.android.blognerdranch.models.Post
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity() {

    @Inject
    lateinit var postViewModel: PostViewModel

    private var postId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        (application as AppController).applicationComponent.newActivityComponent(ActivityModule(this)).inject(this)

        postId = intent.getIntExtra(EXTRA_POST_ID, 0)
        whenNull(savedInstanceState, postViewModel::getPost, postId)

        postViewModel.postLiveData.observe(this, Observer {
            progressBar.visibility = View.INVISIBLE
            updateUI(it)
        })
    }

    private fun updateUI(post: Post) {
        title_textview?.text = post.metadata?.title
        author_textView?.text = post.metadata?.author?.name
        body_textView?.text = post.body
    }

    companion object {
        const val TAG = "PostActivity"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, id)
            return intent
        }
    }
}
