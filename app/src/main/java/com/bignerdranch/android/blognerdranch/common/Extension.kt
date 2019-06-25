package com.bignerdranch.android.blognerdranch.common

import android.os.Bundle
import com.bignerdranch.android.blognerdranch.ui.list.PostListActivity
import com.bignerdranch.android.blognerdranch.ui.post.PostActivity

fun PostActivity?.whenNull(bundle: Bundle?, f: (Int) -> Unit, id: Int) {
    if (bundle == null) {
        f(id)
    }
}

fun PostListActivity.whenNull(bundle: Bundle?, f:() -> Unit) {
    if (bundle == null) {
        this.countingIdlingResource.increment()
        f()
    }
}