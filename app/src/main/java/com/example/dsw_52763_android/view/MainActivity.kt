package com.example.dsw_52763_android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

        FirebaseApp.initializeApp(this)

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
