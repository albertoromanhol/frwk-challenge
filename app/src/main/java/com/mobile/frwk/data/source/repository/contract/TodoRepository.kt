package com.mobile.frwk.data.source.repository.contract

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.model.TodoEntity

interface TodoRepository {
    fun getTodos() : LiveData<List<TodoEntity>>
    suspend fun insertTodos(todos : List<TodoEntity>)
}