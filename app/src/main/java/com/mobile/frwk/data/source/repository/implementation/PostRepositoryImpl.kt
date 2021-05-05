package com.mobile.frwk.data.source.repository.implementation

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.dao.PostDao
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.data.source.repository.contract.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDao : PostDao
    ): PostRepository {

    // <editor-fold desc="[ Public Functions ]">

    override fun getPosts() : LiveData<List<PostEntity>> = postDao.getPosts()

    override suspend fun insertPosts(posts: List<PostEntity>) {
        postDao.insert(posts)
    }

    // </editor-fold>
}