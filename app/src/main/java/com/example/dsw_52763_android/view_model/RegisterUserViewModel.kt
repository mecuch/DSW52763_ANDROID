package com.example.dsw_52763_android.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterUserViewModel : ViewModel() {

    val registrationSuccess = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun registerUser(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        if (password != confirmPassword) {
            errorMessage.value = "Passwords do not match"
            registrationSuccess.value = false
            return
        }

        if (email.isBlank() || password.isBlank() || fullName.isBlank()) {
            errorMessage.value = "All fields must be filled"
            registrationSuccess.value = false
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        // Użytkownik utworzony — teraz tworzymy rekord w Firestore
                        createUserInDatabase(user.uid, email, fullName)
                    }
                } else {
                    errorMessage.value = task.exception?.message ?: "Registration failed"
                    registrationSuccess.value = false
                }
            }
    }

    private fun createUserInDatabase(uid: String, email: String, fullName: String) {
        val noteDbName = "notes_${email.replace("@", "_").replace(".", "_")}"
        val userMap = hashMapOf(
            "email" to email,
            "fullName" to fullName,
            "dbName" to noteDbName,
            "createdAt" to System.currentTimeMillis()
        )

        db.collection("users")
            .document(uid)
            .set(userMap)
            .addOnSuccessListener {
                errorMessage.value = ""
                registrationSuccess.value = true
            }
            .addOnFailureListener { e ->
                errorMessage.value = "Failed to create user data: ${e.message}"
                registrationSuccess.value = false
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
//import com.example.dsw_52763_android.model.Users
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class RegisterUserViewModel : ViewModel() {
//
//    val registrationSuccess = mutableStateOf(false)
//    val errorMessage = mutableStateOf("")
//
//    fun registerUser(
//        context: Context,
//        fullName: String,
//        email: String,
//        password: String,
//        confirmPassword: String,
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val userDao = UserDatabase.getInstance(context).userDao()
//
//            if (password != confirmPassword) {
//                errorMessage.value = "Passwords do not match"
//                return@launch
//            }
//
//            val existingUser = userDao.getUsersByLogin(email)
//            if (existingUser != null) {
//                errorMessage.value = "User already exists"
//                return@launch
//            }
//
//            val noteDbName = "notes_${email.replace("@", "_").replace(".", "_")}"
//
//            val newUser = Users(
//                login = email,
//                password = password,
//                fullName = fullName,
//                dbName = noteDbName
//            )
//
//            userDao.insertUser(newUser)
//
//            errorMessage.value = ""
//            registrationSuccess.value = true
//        }
//    }
//}