package com.example.dsw_52763_android.view_model

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw_52763_android.db.UserDatabase
import com.example.dsw_52763_android.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterUserViewModel : ViewModel() {

    val registrationSuccess = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    fun registerUser(
        context: Context,
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val userDao = UserDatabase.getInstance(context).userDao()

            if (password != confirmPassword) {
                errorMessage.value = "Passwords do not match"
                return@launch
            }

            val existingUser = userDao.getUsersByLogin(email)
            if (existingUser != null) {
                errorMessage.value = "User already exists"
                return@launch
            }

            val noteDbName = "notes_${email.replace("@", "_").replace(".", "_")}"

            val newUser = Users(
                login = email,
                password = password,
                fullName = fullName,
                dbName = noteDbName
            )

            userDao.insertUser(newUser)

            errorMessage.value = ""
            registrationSuccess.value = true
        }
    }
}