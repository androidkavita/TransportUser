package com.govahan.com.module

import com.govahan.com.data.MainRepository
import com.govahan.com.data.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {
    @Binds
    fun mainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}