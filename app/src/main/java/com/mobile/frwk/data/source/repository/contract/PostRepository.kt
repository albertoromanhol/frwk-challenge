package com.mobile.frwk.data.source.repository.contract

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.model.PostEntity

interface PostRepository {
     fun getPosts() : LiveData<List<PostEntity>>
    suspend fun insertPosts(posts : List<PostEntity>)
}