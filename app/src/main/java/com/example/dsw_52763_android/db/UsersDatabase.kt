package com.example.dsw_52763_android.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dsw_52763_android.model.Users

@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao

    companion object {
        @Volatile private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "users.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}