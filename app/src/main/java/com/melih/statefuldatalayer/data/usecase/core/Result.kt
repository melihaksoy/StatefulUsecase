package com.melih.statefuldatalayer.data.usecase.core

sealed class Result<out T, out R> {
    class Success<out T>(val successData: T) : Result<T, Nothing>()
    class Failure<out R : Error>(val errorData: R) : Result<Nothing, R>()

    fun handleResult(sBlock: (T) -> Unit, eBlock: (R) -> Unit) {
        if (this is Success) {
            sBlock(successData)
        } else if (this is Failure) {
            eBlock(errorData)
        }
    }

    fun handleSuccess(block: (T) -> Unit) {
        if (this is Success) block(successData)
    }

    fun handleError(block: (R) -> Unit) {
        if (this is Failure) block(errorData)
    }
}

