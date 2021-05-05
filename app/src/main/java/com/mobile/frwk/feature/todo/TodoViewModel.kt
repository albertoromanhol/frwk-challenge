package com.mobile.frwk.feature.todo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.data.source.repository.contract.TodoRepository

class TodoViewModel @ViewModelInject constructor(
    todoRepository: TodoRepository
): ViewModel() {
    val todos: LiveData<List<TodoEntity>> = todoRepository.getTodos().map { it }
}