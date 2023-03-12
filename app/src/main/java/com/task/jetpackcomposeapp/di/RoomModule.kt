package com.task.jetpackcomposeapp.di

import android.app.Application
import androidx.room.Room
import com.task.jetpackcomposeapp.data.room.PetShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, PetShopDatabase::class.java, "pet_shop.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: PetShopDatabase) = database.petShopDao()
}