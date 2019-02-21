package com.melih.statefuldatalayer.ui.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> AppCompatActivity.observe(data: LiveData<T>, crossinline block: (T) -> Unit) {
    data.observe(this, Observer {
        block(it)
    })
}