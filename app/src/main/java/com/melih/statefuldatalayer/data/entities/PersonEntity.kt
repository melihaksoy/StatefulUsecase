package com.melih.statefuldatalayer.data.entities

data class PersonEntity(
    val id: Int = 0,
    val name: String = "Name",
    val surname: String = "Surname"
) : Entity() {
    override fun toString(): String {
        return "$name $surname"
    }
}