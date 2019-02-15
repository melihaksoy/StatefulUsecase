package com.melih.statefuldatalayer.data.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.sources.NetworkSource
import com.melih.statefuldatalayer.data.sources.PersistenceSource
import com.melih.statefuldatalayer.data.usecase.core.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetPerson @Inject constructor(
    private val networkSource: NetworkSource,
    private val persistenceSource: PersistenceSource
) : BaseUseCase<PersonEntity, None>() {

    override val _result = MutableLiveData<Result<PersonEntity, Error>>()
    override val _state: MutableLiveData<State> = MutableLiveData()

    override suspend fun run(params: None) {
        @Suppress("DeferredResultUnused")
        doAsync {
            _state.postValue(State.LOADING_PERSISTENCE)
            Log.i("Async", "Async Pers")
            delay(1000)
            _result.postValue(persistenceSource.getItems())
            _state.postValue(State.LOAD_COMPLETE_PERSISTENCE)
            Log.i("Async", "Posted Pers")
        }.await()

        @Suppress("DeferredResultUnused")
        doAsync {
            delay(1000)
            _state.postValue(State.LOADING_NETWORK)
            Log.i("Async", "Async Netw")
            delay(5000)
            _result.postValue(networkSource.getItems())
            _state.postValue(State.LOAD_COMPLETE_NETWORK)
            Log.i("Async","Posted Netw")
        }
    }
}