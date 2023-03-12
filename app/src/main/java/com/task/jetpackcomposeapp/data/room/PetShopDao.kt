package com.task.jetpackcomposeapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PetShopDao {
    @Query("SELECT * FROM petShop")
    fun getAll(): Flow<List<PetShopEntity>>

    @Query("SELECT * FROM petShop WHERE isFavorite = 1")
    fun getAllFavorite(): Flow<List<PetShopEntity>>

    @Query("SELECT * FROM petShop WHERE id = :id")
    fun getData(id: Int): Flow<PetShopEntity>

    @Query("SELECT * FROM petShop WHERE name LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<PetShopEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(petShopList: List<PetShopEntity>)

    @Query("UPDATE petShop SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun editFavorite(id: Int, isFavorite: Boolean)
}