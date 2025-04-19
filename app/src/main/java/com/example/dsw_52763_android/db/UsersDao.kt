package com.example.dsw_52763_android.db

import android.annotation.SuppressLint
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.google.firebase.firestore.auth.User

@Dao
interface UsersDao {
    @SuppressLint("RestrictedApi")
    @Query("SELECT * FROM Users WHERE login = :login LIMIT 1")
    suspend fun getUsersByLogin(login: String): User?

    @Insert
    suspend fun insertUser(user: User)
}