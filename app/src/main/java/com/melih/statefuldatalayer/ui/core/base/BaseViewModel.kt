package com.melih.statefuldatalayer.ui.core.base

import androidx.lifecycle.ViewModel
import com.melih.statefuldatalayer.data.entities.Entity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    protected abstract val receiveChannel: ReceiveChannel<Result<Entity, Error>>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    abstract fun resolve(value: Result<Entity, Error>)

    init {
        processStream()
    }

    private fun processStream() {
        launch {
            receiveChannel.consumeEach {
                resolve(it)
            }
        }
    }

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}