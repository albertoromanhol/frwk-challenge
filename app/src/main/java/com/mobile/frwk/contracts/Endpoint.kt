package com.mobile.frwk.contracts

import com.mobile.frwk.models.Albums
import com.mobile.frwk.models.Posts
import com.mobile.frwk.models.Todos
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("posts")
    fun getPosts() : Call<List<Posts>>

    @GET("albums")
    fun getAlbums() : Call<List<Albums>>

    @GET("todos")
    fun getTodos() : Call<List<Todos>>
}