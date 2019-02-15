package com.melih.statefuldatalayer.data.usecase.core

enum class State {
    INITIAL, LOADING_PERSISTENCE, LOADING_NETWORK, LOAD_COMPLETE_PERSISTENCE, LOAD_COMPLETE_NETWORK
}