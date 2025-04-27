package com.example.dsw_52763_android.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginUserViewModel : ViewModel() {

    val loginSuccess = mutableStateOf(false)
    val errorMessage = mutableStateOf("")
    var noteDbName = ""
    var fullName = ""

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun loginUser(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage.value = "Email and password cannot be empty!"
            loginSuccess.value = false
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        // Tutaj pobieramy dane dodatkowe z Firestore
                        viewModelScope.launch {
                            loadUserData(user.uid)
                        }
                    } else {
                        errorMessage.value = "Login failed: user is null"
                        loginSuccess.value = false
                    }
                } else {
                    errorMessage.value = task.exception?.message ?: "Login failed"
                    loginSuccess.value = false
                }
            }
    }

    private fun loadUserData(uid: String) {
        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    noteDbName = "notes_${uid}" // np. nadajemy nazwę bazy po UID albo inną logikę
                    fullName = document.getString("fullName") ?: "User"
                    loginSuccess.value = true
                    errorMessage.value = ""
                } else {
                    errorMessage.value = "User data not found!"
                    loginSuccess.value = false
                }
            }
            .addOnFailureListener { exception ->
                errorMessage.value = "Error loading user data: ${exception.message}"
                loginSuccess.value = false
            }
    }
}




//package com.example.dsw_52763_android.view_model
//
//import android.content.Context
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.dsw_52763_android.db.UserDatabase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class LoginUserViewModel : ViewModel() {
//
//    val loginSuccess = mutableStateOf(false)
//    val errorMessage = mutableStateOf("")
//    var noteDbName = ""
//    var fullName = ""
//
//    fun loginUser(context: Context, login: String, password: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val userDao = UserDatabase.getInstance(context).userDao()
//            val user = userDao.getUsersByLogin(login)
//
//            if (login.isBlank() && password.isBlank()) {
//                errorMessage.value = "Login and password cannot be empty!"
//                loginSuccess.value = false
//                return@launch
//            }
//
//            if (user == null) {
//                errorMessage.value = "User not found!"
//                loginSuccess.value = false
//                return@launch
//            }
//
//            if (user.password != password) {
//                errorMessage.value = "Incorrect password!"
//                loginSuccess.value = false
//                return@launch
//            }
//
//            // Użytkownik zalogowany poprawnie
//            noteDbName = user.dbName
//            fullName = user.fullName
//            errorMessage.value = ""
//            loginSuccess.value = true
//        }
//    }
//}