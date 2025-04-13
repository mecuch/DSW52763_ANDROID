package com.example.dsw_52763_android.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dsw_52763_android.model.ToDo


@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Todo_DB"
    }

    abstract fun getToDoDao() : ToDoDao

}