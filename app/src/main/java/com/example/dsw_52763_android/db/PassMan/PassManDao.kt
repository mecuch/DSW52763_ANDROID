package com.example.dsw_52763_android.db.PassMan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dsw_52763_android.model.PassMan

@Dao
interface PassManDao {
    @Query("SELECT * FROM PassMan WHERE userId = :userId")
    fun getAllPass(userId: String) : LiveData<List<PassMan>>

    @Insert
    fun addRecord(passMan: PassMan)

    @Query("DELETE FROM PassMan where id = :id AND userId = :userId")
    fun deleteRecord(id : Int, userId: String)

}