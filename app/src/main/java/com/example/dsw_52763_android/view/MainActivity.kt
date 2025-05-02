package com.example.dsw_52763_android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.AuthViewModel
import com.example.dsw_52763_android.view_model.PassManModelFactory
import com.example.dsw_52763_android.view_model.PassManViewModel
import com.example.dsw_52763_android.view_model.ToDoViewModel
import com.example.dsw_52763_android.view_model.ToDoViewModelFactory
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ⬇️ WAŻNE! Firebase na samym początku
        FirebaseApp.initializeApp(this)

        // ⬇️ Następnie możesz używać Firebase
        val currentUser = FirebaseAuth.getInstance().currentUser

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            val startDestination = if (currentUser != null) {
                "homePage/${currentUser.uid}"
            } else {
                routes.loginPage
            }
            NavHost(navController = navController, startDestination = startDestination) {
                composable(routes.loginPage) {
                    val authViewModel: AuthViewModel = viewModel()
                    LoginPage(navController = navController, authViewModel = authViewModel)
                }
                composable(routes.registerPage) {
                    val authViewModel: AuthViewModel = viewModel()
                    RegisterPage(navController = navController, authViewModel = authViewModel)
                }
                composable(
                    route = "homePage/{userId}",
                    arguments = listOf(
                        navArgument("userId") { defaultValue = "" }
                    )
                ) {HomePage(navController = navController)}
                composable(
                    route = "notesPage/{userId}",
                    arguments = listOf(
                        navArgument("userId") { defaultValue = "" }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""

                    val factory = ToDoViewModelFactory(applicationContext, userId)
                    val toDoViewModel: ToDoViewModel = viewModel(factory = factory)

                    NotesPage(navController = navController, viewModel = toDoViewModel)
                }
                composable(
                    route = "passmanPage/{userId}",
                    arguments = listOf(
                        navArgument("userId") { defaultValue = "" }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""

                    val factory = PassManModelFactory(applicationContext, userId)
                    val passManViewModel: PassManViewModel = viewModel(factory = factory)

                    PassManPage(navController = navController, viewModel = passManViewModel)
                }
            }
        }
    }
}



//package com.example.dsw_52763_android.view
//
//import android.content.Context
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.dsw_52763_android.utils.routes
//import com.example.dsw_52763_android.view_model.ToDoViewModel
//import com.google.firebase.FirebaseApp
//import com.google.firebase.auth.FirebaseAuth
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
//        enableEdgeToEdge()
//        setContent {
//            FirebaseApp.initializeApp(this)
//            val navController = rememberNavController()
//            val currentUser = FirebaseAuth.getInstance().currentUser
//            val emailSafe = currentUser?.email?.replace("@", "_")?.replace(".", "_") ?: "default"
//            val fullName = currentUser?.displayName ?: "User"
//            val startDestination = if (currentUser != null) {
//                "homePage/notes_$emailSafe/$fullName"
//            } else {
//                routes.loginPage
//            }
//            NavHost(navController = navController, startDestination = startDestination, builder = {
//                composable(routes.loginPage){
//                    LoginPage(navController = navController)
//                }
//                composable(routes.registerPage){
//                    RegisterPage(navController = navController)
//                }
//                composable(
//                    route = "homePage/{dbName}/{fullName}",
//                    arguments = listOf(
//                        navArgument("dbName") { defaultValue = "default.db" },
//                        navArgument("fullName") { defaultValue = "User" }
//                    )
//                ) { backStackEntry ->
//                    val dbName = backStackEntry.arguments?.getString("dbName") ?: "default.db"
//                    val fullName = backStackEntry.arguments?.getString("fullName") ?: "User"
//                    HomePage(viewModel = toDoViewModel, navController = navController, dbName, fullName)
//                }
//            })
//        }
//    }
//}
//
