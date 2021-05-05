package com.mobile.frwk.data.source.repository.contract

import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.data.local.database.model.TodoEntity
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("posts")
    fun getPosts(): Call<List<PostEntity>>

    @GET("albums")
    fun getAlbums(): Call<List<AlbumEntity>>

    @GET("todos")
    fun getTodos(): Call<List<TodoEntity>>
}
