package com.melih.statefuldatalayer.ui

import androidx.databinding.ObservableField
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.GetPerson
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.State
import com.melih.statefuldatalayer.ui.core.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getPerson: GetPerson) : BaseViewModel() {

    val result = getPerson.result
    val state = getPerson.state

    val currentValue = ObservableField<String>()
    val currentState = ObservableField<String>()

    init {
        getPerson(None())
    }

    fun handleSuccess(data: PersonEntity) {
        currentValue.set(data.toString())
    }

    fun handleFailure(error: Error) {
        currentValue.set(error.javaClass.simpleName)
    }

    fun updateState(newState: State) {
        currentState.set(newState.name)
    }
}