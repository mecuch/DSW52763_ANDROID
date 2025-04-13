package com.example.dsw_52763_android.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw_52763_android.MainApplication
import com.example.dsw_52763_android.db.ToDoDao
import com.example.dsw_52763_android.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel : ViewModel() {
    val todoDao = MainApplication.toDoDatabase.getToDoDao()

    val todoList : LiveData<List<ToDo>> = todoDao.getAllToDo()

    fun addToDo(title : String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(ToDo(title = title, createdAt =
            Date.from(Instant.now())))
        }
    }

    fun deleteToDo(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteToDo(id)
        }
    }
}