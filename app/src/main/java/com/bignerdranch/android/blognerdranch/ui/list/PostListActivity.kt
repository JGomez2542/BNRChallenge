package com.bignerdranch.android.blognerdranch.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.bignerdranch.android.blognerdranch.AppController
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.common.ifNull
import com.bignerdranch.android.blognerdranch.di.activity.ActivityModule
import kotlinx.android.synthetic.main.activity_post_list.*
import javax.inject.Inject

/**
 * Displays a list of posts
 */
class PostListActivity : AppCompatActivity() {

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
                adapter = it
            }
        })
    }
}
