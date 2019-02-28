package com.melih.statefuldatalayer.data.usecase.core

sealed class Error {

    class NetworkError() : Error()
    class GenericError() : Error()
    class ResponseError() : Error()
    class PersistenceError() : Error()
}