package com.mobile.frwk.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "title") val title: String = "",
)
