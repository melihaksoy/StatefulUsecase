package com.melih.statefuldatalayer.data.sources

import com.melih.statefuldatalayer.data.entities.ItemEntity
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result
import javax.inject.Inject
import kotlin.random.Random


class NetworkSource @Inject constructor() : Repository {

    /**
     * Assume this function fetches response from network
     */
    override fun getPerson(): Result<PersonEntity, Error> =
        if (Random.nextFloat() > 0.8f) {
            Result.Failure(Error.NetworkError())
        } else {
            Result.Success(PersonEntity(0, "Network", "Source"))
        }

    /**
     * Assume this function fetches response from network
     */
    override fun getItems(personId: Int): Result<ItemEntity, Error> =
        if (Random.nextFloat() > 0.8f) {
            Result.Failure(Error.NetworkError())
        } else {
            Result.Success(ItemEntity("Online !"))
        }
}