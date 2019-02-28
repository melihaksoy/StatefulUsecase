package com.melih.statefuldatalayer.data.usecase

import com.melih.statefuldatalayer.data.sources.NetworkSource
import com.melih.statefuldatalayer.data.sources.PersistenceSource
import com.melih.statefuldatalayer.data.usecase.core.BaseUseCase
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

@Suppress("DeferredResultUnused")
class GetAllData @Inject constructor(
    private val networkSource: NetworkSource,
    private val persistenceSource: PersistenceSource
) : BaseUseCase<None>() {

    override suspend fun run(params: None) {

        doAsync {
            _resultChannel.send(Result.State.Loading())
            delay(1000)
            _resultChannel.send(persistenceSource.getPerson())
            _resultChannel.send(persistenceSource.getItems())
            _resultChannel.send(Result.State.Loaded())
        }.await()

        doAsync {
            delay(1000)
            _resultChannel.send(Result.State.Loading())
            delay(5000)
            _resultChannel.send(persistenceSource.getPerson())
            _resultChannel.send(persistenceSource.getItems())
            _resultChannel.send(Result.State.Loaded())
        }
    }
}