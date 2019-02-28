package com.melih.statefuldatalayer.data.entities

data class InventoryEntity(
    val itemName: String = "Simple Item"
) : Entity {
    override fun toString(): String {
        return itemName
    }
}