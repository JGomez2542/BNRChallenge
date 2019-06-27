package com.bignerdranch.android.blognerdranch.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.bignerdranch.android.blognerdranch.AppController
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.adapters.PostAdapter
import com.bignerdranch.android.blognerdranch.adapters.PostViewHolder
import com.bignerdranch.android.blognerdranch.common.EXTRA_POST_ID
import com.bignerdranch.android.blognerdranch.common.ifNull
import com.bignerdranch.android.blognerdranch.di.activity.ActivityModule
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import com.bignerdranch.android.blognerdranch.ui.post.PostActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.recycler_list_item.*
import javax.inject.Inject

/**
 * Displays a list of posts
 */
class PostListActivity : AppCompatActivity(), PostAdapter.OnClickListener {

    @Inject
    lateinit var postListViewModel: PostListViewModel
    val countingIdlingResource = CountingIdlingResource("PostListActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        (application as AppController).applicationComponent.newActivityComponent(ActivityModule(this)).inject(this)

        savedInstanceState.ifNull {
            countingIdlingResource.increment()
            postListViewModel.getPostList()
        }

        postListViewModel.postAdapterLiveData.observe(this, Observer {
            if (!countingIdlingResource.isIdleNow) {
                countingIdlingResource.decrement()
            }
            post_recyclerview.apply {
                layoutManager = LinearLayoutManager(this@PostListActivity)
                addItemDecoration(DividerItemDecoration(this@PostListActivity, RecyclerView.VERTICAL))
                adapter = PostAdapter(it, this@PostListActivity)
            }
        })
    }

    override fun onClick(holder: PostViewHolder, postMetadata: PostMetadata) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(EXTRA_POST_ID, postMetadata.postId)
        val transNameTitle = resources.getString(R.string.shared_title_trans)
        val transNameAuthor = resources.getString(R.string.shared_author_trans)

        val transTitle = Pair.create<View, String>(holder.textView_title, transNameTitle)
        val transAuthor = Pair.create<View, String>(holder.textView_author, transNameAuthor)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, transTitle, transAuthor)
        startActivity(intent, options.toBundle())
    }
}
