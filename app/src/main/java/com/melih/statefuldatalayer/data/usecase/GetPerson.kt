package com.melih.statefuldatalayer.data.usecase

import com.melih.statefuldatalayer.data.sources.NetworkSource
import com.melih.statefuldatalayer.data.sources.PersistenceSource
import com.melih.statefuldatalayer.data.usecase.core.BaseUseCase
import com.melih.statefuldatalayer.data.usecase.core.None
import kotlinx.coroutines.delay
import javax.inject.Inject

@Suppress("DeferredResultUnused")
class GetPerson @Inject constructor(
    private val networkSource: NetworkSource,
    private val persistenceSource: PersistenceSource
) : BaseUseCase<None>() {

    override suspend fun run(params: None) {

        doAsync {
            delay(1000)
            _resultChannel.send(persistenceSource.getItems())
        }.await()

        doAsync {
            delay(5000)
            _resultChannel.send(networkSource.getItems())
        }
    }
}