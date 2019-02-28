package com.melih.statefuldatalayer.data

import com.melih.statefuldatalayer.data.entities.InventoryEntity
import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result

interface Repository {

    fun getPerson(): Result<PersonEntity, Error>
    fun getItems(): Result<InventoryEntity, Error>
}