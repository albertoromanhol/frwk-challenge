package com.mobile.frwk.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mobile.frwk.data.local.database.model.AlbumEntity

@Dao
abstract class AlbumDao : BaseDao<AlbumEntity>() {
    @Query("SELECT * from album")
    abstract fun getAlbums(): LiveData<List<AlbumEntity>>

}
