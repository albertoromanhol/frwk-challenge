package com.mobile.frwk.feature.post

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.data.source.repository.contract.PostRepository

class PostViewModel @ViewModelInject constructor(
    postRepository: PostRepository
) : ViewModel() {
    val posts: LiveData<List<PostEntity>> = postRepository.getPosts().map { it }
}
