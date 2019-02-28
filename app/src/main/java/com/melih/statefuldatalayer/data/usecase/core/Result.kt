package com.melih.statefuldatalayer.data.usecase.core

sealed class Result<out T, out R> {
    class Success<out T>(val successData: T) : Result<T, Nothing>()
    class Failure<out R : Error>(val errorData: R) : Result<Nothing, R>()

    sealed class State : Result<Nothing, Nothing>() {
        class Loading : State()
        class Loaded : State()
    }

    fun handleResult(stateBlock: (State) -> Unit = {}, failureBlock: (R) -> Unit = {}, successBlock: (T) -> Unit = {}) {
        when (this) {
            is Success -> successBlock(successData)
            is Failure -> failureBlock(errorData)
            is State -> stateBlock(this)
        }
    }
}

