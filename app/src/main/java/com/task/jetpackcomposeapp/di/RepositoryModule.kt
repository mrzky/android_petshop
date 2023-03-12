package com.task.jetpackcomposeapp.di

import com.task.jetpackcomposeapp.data.repository.PetShopRepository
import com.task.jetpackcomposeapp.data.room.PetShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(petShopDao: PetShopDao) = PetShopRepository(petShopDao)
}