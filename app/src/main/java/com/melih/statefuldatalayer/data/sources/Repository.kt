package com.melih.statefuldatalayer.data.sources

import com.melih.statefuldatalayer.data.entities.ItemEntity
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result

interface Repository {

    fun getPerson(): Result<PersonEntity, Error>
    fun getItems(personId: Int): Result<ItemEntity, Error>
}