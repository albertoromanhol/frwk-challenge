package com.mobile.frwk.data.source.repository.contract

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.model.AlbumEntity

interface AlbumRepository {
    fun getAlbums() : LiveData<List<AlbumEntity>>
    suspend fun insertAlbums(albums : List<AlbumEntity>)
}