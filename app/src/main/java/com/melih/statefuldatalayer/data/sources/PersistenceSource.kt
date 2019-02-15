package com.melih.statefuldatalayer.data.sources

import com.melih.statefuldatalayer.data.Repository
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result
import javax.inject.Inject
import kotlin.random.Random

class PersistenceSource @Inject constructor() : Repository {

    /**
     * Assume this function fetches response from persistence
     */
    override fun getItems(): Result<PersonEntity, Error> =
        if (Random.nextFloat() > 0.8f) {
            Result.Failure(Error.ResponseError())
        } else {
            Result.Success(PersonEntity("Persistence", "Source"))
        }
}