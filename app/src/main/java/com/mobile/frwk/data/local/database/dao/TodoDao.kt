package com.mobile.frwk.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mobile.frwk.data.local.database.model.TodoEntity

@Dao
abstract class TodoDao : BaseDao<TodoEntity>() {
    @Query("SELECT * from todo")
    abstract fun getTodos(): LiveData<List<TodoEntity>>
}
