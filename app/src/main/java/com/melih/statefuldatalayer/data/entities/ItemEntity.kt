package com.melih.statefuldatalayer.data.entities

data class ItemEntity(
    val itemName: String = "Simple Item"
) : Entity() {
    override fun toString(): String {
        return itemName
    }
}