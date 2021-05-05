package com.mobile.frwk.data.source.repository.implementation

import androidx.lifecycle.LiveData
import com.mobile.frwk.data.local.database.dao.TodoDao
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.data.source.repository.contract.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {
    override fun getTodos(): LiveData<List<TodoEntity>> = todoDao.getTodos()

    override suspend fun insertTodos(todos: List<TodoEntity>) {
        todoDao.insert(todos)
    }
}