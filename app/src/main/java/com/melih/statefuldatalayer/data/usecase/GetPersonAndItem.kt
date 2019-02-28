package com.melih.statefuldatalayer.data.usecase

import com.melih.statefuldatalayer.data.sources.NetworkSource
import com.melih.statefuldatalayer.data.sources.PersistenceSource
import com.melih.statefuldatalayer.data.usecase.core.BaseUseCase
import com.melih.statefuldatalayer.data.usecase.core.None
import com.melih.statefuldatalayer.data.usecase.core.Result
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

@Suppress("DeferredResultUnused")
class GetPersonAndItem @Inject constructor(
    private val networkSource: NetworkSource,
    private val persistenceSource: PersistenceSource
) : BaseUseCase<None>() {

    override suspend fun run(params: None) {

        // Started loading
        resultChannel.send(Result.State.Loading())

        // Get person from persistence and send it, synchronous
        delay(1000)
        resultChannel.send(persistenceSource.getPerson())

        // Get person from network and send it, synchronous
        delay(2000)
        val personFromNetwork = networkSource.getPerson()
        resultChannel.send(personFromNetwork)

        personFromNetwork.handleResult {
            // Got person from network succesfully

            // Save person asynchronously
            startAsync {
                delay(500)
                persistenceSource.savePerson(it)
            }

            startAsync {
                // Starting persistence async first
                val itemPersistenceAsync = startAsync {
                    delay(Random.nextLong(1000, 3000))
                    resultChannel.send(persistenceSource.getItems(it.id))
                }

                // Starting network async
                val itemNetworkAsync = startAsync {
                    delay(Random.nextLong(1000, 3000))
                    resultChannel.send(networkSource.getItems(it.id))

                    // Cancel persistence async if it's still active
                    if (itemPersistenceAsync.isActive)
                        itemPersistenceAsync.cancel()
                }

                awaitAll(itemPersistenceAsync, itemNetworkAsync)

                // Hey, I'm done !
                resultChannel.send(Result.State.Loaded())
            }
        }
    }
}