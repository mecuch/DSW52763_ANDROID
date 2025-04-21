package com.example.dsw_52763_android.db

import android.annotation.SuppressLint
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dsw_52763_android.model.Users



@Dao
interface UsersDao {
    @SuppressLint("RestrictedApi")
    @Query("SELECT * FROM Users WHERE login = :login")
    suspend fun getUsersByLogin(login: String): Users?

    @Insert
    suspend fun insertUser(user: Users)
}