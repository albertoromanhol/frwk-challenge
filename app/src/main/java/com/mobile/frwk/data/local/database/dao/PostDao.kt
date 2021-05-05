package com.mobile.frwk.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mobile.frwk.data.local.database.model.PostEntity

@Dao
abstract class PostDao : BaseDao<PostEntity>() {
    @Query("SELECT * from post")
    abstract fun getPosts(): LiveData<List<PostEntity>>
}
