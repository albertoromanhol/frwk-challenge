package com.mobile.frwk.data.source.repository.implementation

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.dao.AlbumDao
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.source.repository.contract.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao : AlbumDao
): AlbumRepository {
    override fun getAlbums() : LiveData<List<AlbumEntity>> = albumDao.getAlbums()


    override suspend fun insertAlbums(albums: List<AlbumEntity>) {
        albumDao.insert(albums)
    }
}