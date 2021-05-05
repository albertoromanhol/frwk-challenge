package com.mobile.frwk.core.di

import com.mobile.frwk.data.source.repository.contract.AlbumRepository
import com.mobile.frwk.data.source.repository.contract.PostRepository
import com.mobile.frwk.data.source.repository.contract.TodoRepository
import com.mobile.frwk.data.source.repository.implementation.AlbumRepositoryImpl
import com.mobile.frwk.data.source.repository.implementation.PostRepositoryImpl
import com.mobile.frwk.data.source.repository.implementation.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModules {
    @Binds
    abstract fun bindAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

    @Binds
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    abstract fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository

}