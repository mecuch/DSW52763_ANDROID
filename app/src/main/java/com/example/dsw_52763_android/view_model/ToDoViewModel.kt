package com.example.dsw_52763_android.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw_52763_android.MainApplication
import com.example.dsw_52763_android.db.ToDoDao
import com.example.dsw_52763_android.db.ToDoDatabase
import com.example.dsw_52763_android.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel(application: Application, private val userId: String) : AndroidViewModel(application) {

    private val todoDao = ToDoDatabase.getDatabase(application).getToDoDao()
    val allNotes: LiveData<List<ToDo>> = todoDao.getAllToDo(userId)

    fun addNote(title: String) {
        val note = ToDo(title = title, createdAt = Date(), userId = userId)
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(note)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteToDo(id, userId)
        }
    }
}