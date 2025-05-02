package com.example.dsw_52763_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PassMan(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var urls: String,
    var logins: String,
    var passwords: String,
    var userId: String
)
