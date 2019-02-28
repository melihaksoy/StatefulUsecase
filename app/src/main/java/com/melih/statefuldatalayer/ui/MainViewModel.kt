package com.melih.statefuldatalayer.ui

import androidx.databinding.ObservableField
import com.melih.statefuldatalayer.data.entities.Entity
import com.melih.statefuldatalayer.data.entities.ItemEntity
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.GetPersonAndItem
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.Result
import com.melih.statefuldatalayer.ui.core.base.BaseViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

@ObsoleteCoroutinesApi
class MainViewModel @Inject constructor(private val getPersonAndItem: GetPersonAndItem)
    : BaseViewModel() {

    val currentUser = ObservableField<String>()
    val currentItem = ObservableField<String>()
    val currentState = ObservableField<String>()

    override val receiveChannel: ReceiveChannel<Result<Entity, Error>>
        get() = getPersonAndItem.receiveChannel

    init {
        getPersonAndItem(None())
    }

    override fun resolve(value: Result<Entity, Error>) {
        value.handleResult(::handleState, ::handleFailure, ::handleSuccess)
    }

    fun handleSuccess(data: Entity) {
        when (data) {
            is PersonEntity -> currentUser.set(data.toString())
            is ItemEntity -> currentItem.set(data.toString())
        }
    }

    fun handleFailure(error: Error) {
        currentUser.set(error::class.simpleName)
    }

    fun handleState(state: Result.State) {
        currentState.set(state::class.simpleName)
    }
}