package com.bignerdranch.android.blognerdranch

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.bignerdranch.android.blognerdranch.adapters.PostViewHolder
import com.bignerdranch.android.blognerdranch.ui.list.PostListActivity
import com.bignerdranch.android.blognerdranch.ui.post.PostActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class PostListActivityTest {

    @get:Rule
    val activityRule: ActivityTestRule<PostListActivity> = ActivityTestRule(
        PostListActivity::class.java,
        false,
        true
    )


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(activityRule.activity.countingIdlingResource)
        activityRule.launchActivity(Intent())
        Intents.init()
    }

    @Test
    fun recyclerView_isNotEmpty() {
        val rvCount = activityRule.activity.post_recyclerview.adapter?.itemCount ?: 0
        assert(rvCount > 0)
    }

    @Test
    fun shouldLaunchPostActivity_whenItemIsClicked() {
        onView(withId(R.id.post_recyclerview))
            .perform(actionOnItemAtPosition<PostViewHolder>(0, click()))
        intended(hasComponent(PostActivity::class.java.name))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(activityRule.activity.countingIdlingResource)
        activityRule.finishActivity()
        Intents.release()
    }
}