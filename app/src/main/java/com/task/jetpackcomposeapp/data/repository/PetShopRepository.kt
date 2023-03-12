package com.task.jetpackcomposeapp.data.repository

import com.task.jetpackcomposeapp.data.room.PetShopDao
import com.task.jetpackcomposeapp.data.room.PetShopEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetShopRepository @Inject constructor(private val petShopDao: PetShopDao) {
    fun getAll() = petShopDao.getAll()
    fun getAllFavorite() = petShopDao.getAllFavorite()
    fun getData(id: Int) = petShopDao.getData(id)
    fun search(query: String) = petShopDao.search(query)
    suspend fun addAll(data: List<PetShopEntity>) = petShopDao.addAll(data)
    suspend fun editFavorite(id: Int, isFavorite: Boolean) = petShopDao.editFavorite(id, isFavorite)
}