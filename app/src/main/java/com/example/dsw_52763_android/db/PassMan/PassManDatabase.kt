package com.example.dsw_52763_android.db.PassMan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.dsw_52763_android.model.PassMan

@Database(entities = [PassMan::class], version = 1, exportSchema = false)
abstract class PassManDatabase : RoomDatabase() {

    abstract fun getPassManDao(): PassManDao

    companion object {
        @Volatile
        private var INSTANCE: PassManDatabase? = null

        fun getDatabase(context: Context): PassManDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassManDatabase::class.java,
                    "Todo_DB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}