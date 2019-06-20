package com.bignerdranch.android.blognerdranch.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity?.whenNull(bundle: Bundle?, f: (Int) -> Unit, id: Int) {
    if (bundle == null) {
        f(id)
    }
}