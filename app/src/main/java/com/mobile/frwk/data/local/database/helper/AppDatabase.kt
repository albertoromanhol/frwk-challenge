package com.mobile.frwk.data.local.database.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.frwk.data.local.database.dao.AlbumDao
import com.mobile.frwk.data.local.database.dao.PostDao
import com.mobile.frwk.data.local.database.dao.TodoDao
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.data.local.database.model.TodoEntity

@Database(
    entities = [
        AlbumEntity::class,
        PostEntity::class,
        TodoEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun postDao(): PostDao
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "frwkdatabase.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
