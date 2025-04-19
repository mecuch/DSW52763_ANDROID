package com.example.dsw_52763_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val login: String,
    val password: String,
    val dbName: String
)
