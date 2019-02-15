package com.melih.statefuldatalayer.data.sources

import com.melih.statefuldatalayer.data.Repository
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Error.ResponseError
import com.melih.statefuldatalayer.data.usecase.core.Result
import javax.inject.Inject
import kotlin.random.Random


class NetworkSource @Inject constructor() : Repository {

    /**
     * Assume this function fetches response from network
     */
    override fun getItems(): Result<PersonEntity, Error> =
        if (Random.nextFloat() > 0.8f) {
            Result.Failure(ResponseError())
        } else {
            Result.Success(PersonEntity("Network", "Source"))
        }
}