package com.task.jetpackcomposeapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "petShop")
data class PetShopEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val about: String,
    val image: String,
    val address: String,
    val rating: Double,
    var isFavorite: Boolean = false,
)
