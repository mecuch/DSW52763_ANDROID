package com.example.dsw_52763_android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = routes.loginPage, builder = {
                composable(routes.loginPage){
                    LoginPage(navController = navController)
                }
                composable(routes.registerPage){
                    RegisterPage(navController = navController)
                }
                composable(routes.homePage){
                    HomePage(viewModel = toDoViewModel, navController = navController)
                }
            })
        }
    }
}

