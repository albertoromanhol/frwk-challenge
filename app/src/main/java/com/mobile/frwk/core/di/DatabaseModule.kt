package com.mobile.frwk.core.di

import android.content.Context
import com.mobile.frwk.data.local.database.dao.AlbumDao
import com.mobile.frwk.data.local.database.dao.PostDao
import com.mobile.frwk.data.local.database.dao.TodoDao
import com.mobile.frwk.data.local.database.helper.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun bindAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.albumDao()
    }

    @Provides
    fun bindPostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

    @Provides
    fun bindTodoDao(appDatabase: AppDatabase): TodoDao {
        return appDatabase.todoDao()
    }
}