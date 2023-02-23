package com.test.repository_doors

import com.test.core_db.domain.DatabaseAccess
import com.test.core_network.data.NetworkApi
import com.test.repository_doors.data.DoorsRepositoryImpl
import com.test.repository_doors.domain.DoorsRepository
import com.test.repository_doors.domain.DoorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DoorsRepositoryModule {

    @Provides
    fun provideDoorsRepository(api: NetworkApi, db: DatabaseAccess): DoorsRepository =
        DoorsRepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideDoorsRepositoryUseCase(repository: DoorsRepository) = DoorsUseCase(repository)
}