package com.test.core_db

import com.test.core_db.data.DatabaseAccessImpl
import com.test.core_db.domain.DatabaseAccess
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val REALM_VERSION = 1L

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(REALM_VERSION)
            .build()
    @Singleton
    @Provides
    fun provideDatabaseAccess(config: RealmConfiguration): DatabaseAccess =
        DatabaseAccessImpl(config)

}