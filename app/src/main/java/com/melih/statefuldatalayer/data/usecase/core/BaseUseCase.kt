package com.melih.statefuldatalayer.data.usecase.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out T, in Params> : CoroutineScope {

    // region Members
    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    val result: LiveData<out Result<T, Error>>
        get() = _result

    val state: LiveData<State>
        get() = _state

    abstract protected val _result: MutableLiveData<out Result<T, Error>>
    abstract protected val _state: MutableLiveData<State>

    // endregion

    // region Functions
    protected abstract suspend fun run(params: Params)

    /**
     * By overriding invoke, we allow use cases to be called as "invoking"
     */
    operator fun invoke(params: Params) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }

    protected fun <T> doAsync(block: suspend () -> T): Deferred<T> = async(parentJob) {
        block()
    }

    /**
     * Should be called when use-case owner is destroyed
     * This will ensure coroutine is cancelled if it's still running some tasks
     */
    fun clear() {
        parentJob.cancel()
    }
    // endregion
}

class None : Any()