package com.melih.statefuldatalayer.ui

import androidx.databinding.ObservableField
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.GetPerson
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.Result
import com.melih.statefuldatalayer.ui.core.base.BaseViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

@ObsoleteCoroutinesApi
class MainViewModel @Inject constructor(private val getPerson: GetPerson) : BaseViewModel<PersonEntity>() {

    val currentValue = ObservableField<String>()
    val currentState = ObservableField<String>()

    override val receiveChannel: ReceiveChannel<Result<PersonEntity, Error>>
        get() = getPerson.receiveChannel

    init {
        getPerson(None())
    }

    override fun resolve(value: Result<PersonEntity, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    fun handleSuccess(data: PersonEntity) {
        currentValue.set(data.toString())
    }

    fun handleFailure(error: Error) {
        currentValue.set(error.javaClass.simpleName)
    }

    fun handleState(state: Result.State) {
        currentState.set(state::class.simpleName)
    }
}