package com.melih.statefuldatalayer.data.entities

data class PersonEntity(
    val name: String = "Name",
    val surname: String = "Surname"
) {
    override fun toString(): String {
        return "$name $surname"
    }
}