package com.example.dsw_52763_android.view_model
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dsw_52763_android.db.PassMan.PassManDatabase
import com.example.dsw_52763_android.model.PassMan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PassManViewModel(application: Application, private val userId: String) : AndroidViewModel(application) {

    private val passManDao = PassManDatabase.getDatabase(application).getPassManDao()
    val allPasswords: LiveData<List<PassMan>> = passManDao.getAllPass(userId)

    fun addPass(urls: String, logins: String, passwords: String) {
        val pass = PassMan(urls = urls, logins = logins, passwords = passwords, userId = userId)
        viewModelScope.launch(Dispatchers.IO) {
            passManDao.addRecord(pass)
        }
    }

    fun deletePass(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            passManDao.deleteRecord(id, userId)
        }
    }
}
