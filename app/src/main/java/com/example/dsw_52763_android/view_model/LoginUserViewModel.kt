package com.example.dsw_52763_android.view_model

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw_52763_android.db.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginUserViewModel : ViewModel() {

    val loginSuccess = mutableStateOf(false)
    val errorMessage = mutableStateOf("")
    var noteDbName = ""
    var fullName = ""

    fun loginUser(context: Context, login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userDao = UserDatabase.getInstance(context).userDao()
            val user = userDao.getUsersByLogin(login)

            if (user == null) {
                errorMessage.value = "User not found!"
                loginSuccess.value = false
                return@launch
            }

            if (user.password != password) {
                errorMessage.value = "Incorrect password!"
                loginSuccess.value = false
                return@launch
            }

            // Użytkownik zalogowany poprawnie
            noteDbName = user.dbName
            fullName = user.fullName
            errorMessage.value = ""
            loginSuccess.value = true
        }
    }
}