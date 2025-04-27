package com.example.dsw_52763_android

import android.app.Application
import androidx.room.Room
import com.example.dsw_52763_android.db.ToDoDatabase
import com.google.firebase.FirebaseApp

class MainApplication : Application() {

    companion object {
        lateinit var toDoDatabase: ToDoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        toDoDatabase = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
    }
}