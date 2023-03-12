package com.task.jetpackcomposeapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PetShopEntity::class], version = 1, exportSchema = false)
abstract class PetShopDatabase : RoomDatabase() {
    abstract fun petShopDao(): PetShopDao
}