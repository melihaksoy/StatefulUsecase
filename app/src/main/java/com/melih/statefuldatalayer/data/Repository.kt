package com.melih.statefuldatalayer.data

import com.melih.statefuldatalayer.data.entities.PersonEntity
import com.melih.statefuldatalayer.data.usecase.core.Error
import com.melih.statefuldatalayer.data.usecase.core.Result

interface Repository {

    fun getItems(): Result<PersonEntity, Error>
}