package com.bignerdranch.android.blognerdranch.common

fun Any?.ifNull(block: () -> Unit) {
    if (this == null) block()
}