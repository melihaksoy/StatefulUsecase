package com.melih.statefuldatalayer.ui

import android.util.Log
import androidx.databinding.ObservableField
import com.melih.statefuldatalayer.data.entities.Entity
import com.melih.statefuldatalayer.data.entities.InventoryEntity
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.GetAllData
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.Result
import com.melih.statefuldatalayer.ui.core.base.BaseViewModel
import com.melih.statefuldatalayer.ui.core.extensions.addOnPropertyChangedCallback
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

@ObsoleteCoroutinesApi
class MainViewModel @Inject constructor(private val getAllData: GetAllData) : BaseViewModel() {

    val currentUser = ObservableField<String>().apply {
        addOnPropertyChangedCallback { sender, propertyId ->
            Log.i("Data", "${get()}")
        }
    }

    val currentItem = ObservableField<String>().apply {
        addOnPropertyChangedCallback { sender, propertyId ->
            Log.i("Data", "${get()}")
        }
    }

    val currentState = ObservableField<String>().apply {
        addOnPropertyChangedCallback { sender, propertyId ->
            Log.i("Data", "${get()}")
        }
    }

    override val receiveChannel: ReceiveChannel<Result<Entity, Error>>
        get() = getAllData.receiveChannel

    init {
        getAllData(None())
    }

    override fun resolve(value: Result<Entity, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    fun handleSuccess(data: Entity) {
        when (data) {
            is PersonEntity -> currentUser.set(data.toString())
            is InventoryEntity -> currentItem.set(data.toString())
        }
    }

    fun handleFailure(error: Error) {
        currentUser.set(error.javaClass.simpleName)
    }

    fun handleState(state: Result.State) {
        currentState.set(state::class.simpleName)
    }
}