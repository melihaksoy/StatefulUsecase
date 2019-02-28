package com.melih.statefuldatalayer.ui.core.extensions

import androidx.databinding.Observable

fun Observable.addOnPropertyChangedCallback(block: (sender: Observable?, propertyId: Int) -> Unit) {
    addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            block(sender, propertyId)
        }
    })
}