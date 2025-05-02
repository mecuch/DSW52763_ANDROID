package com.example.dsw_52763_android.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dsw_52763_android.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllToDo(userId: String) : LiveData<List<ToDo>>

    @Insert
    fun addTodo(todo : ToDo)

    @Query("DELETE FROM ToDo where id = :id AND userId = :userId")
    fun deleteToDo(id : Int, userId: String)

    }