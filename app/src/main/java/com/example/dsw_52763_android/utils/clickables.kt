package com.example.dsw_52763_android.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dsw_52763_android.view_model.LoginUserViewModel
import com.example.dsw_52763_android.view_model.RegisterUserViewModel

object clickables {
    @Composable
    fun RegisterClickableButton(navController: NavController,
                                destinations: String,
                                username: String,
                                emails: String,
                                password: String,
                                confirmpassword: String) {
        val context = LocalContext.current
        val viewModel: RegisterUserViewModel = viewModel()
        Button(onClick = {
            viewModel.registerUser(
                context = context,
                fullName = username,
                email = emails,
                password = password,
                confirmPassword = confirmpassword
            )
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.ornamentscolor,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text("Sign Up")
        }
        if (viewModel.registrationSuccess.value) {
            // Automatycznie przekieruj do logowania po sukcesie
            LaunchedEffect(Unit) {
                navController.navigate(destinations)
            }
            non_clickables.HeaderText("User created succesfully!")
        }

        if (viewModel.errorMessage.value.isNotEmpty()) {
            non_clickables.HeaderText("❗ ${viewModel.errorMessage.value}")
        }
    }

    @Composable
    fun LoginClickableButton(navController: NavController, username: String, password: String) {
        val context = LocalContext.current
        val viewModel: LoginUserViewModel = viewModel()
        Button(onClick = {
            viewModel.loginUser(context, username, password)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.ornamentscolor,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text("Sign in")
        }
        if (viewModel.loginSuccess.value) {
            LaunchedEffect(Unit) {
                navController.navigate("homePage/${viewModel.noteDbName}/${viewModel.fullName}")
            }
            non_clickables.HeaderText("Login Succesfull!")
        }

        if (viewModel.errorMessage.value.isNotEmpty()) {
            non_clickables.HeaderText("❗ ${viewModel.errorMessage.value}")
        }
    }

    @Composable
    fun ClickableBack(navController: NavController) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Wstecz",
            )
            Text("back",
                fontSize = 18.sp,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(routes.loginPage) }
            )
        }
    }

    @Composable
    fun ClickableButton(navController: NavController, destinations: String, title: String) {
        Button(onClick = {
            navController.navigate(destinations)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.ornamentscolor,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text(title)
        }
    }

    @Composable
    fun ClickableText(navController: NavController, nonclick: String, click: String, destination: String) {
        Row {
            Text(nonclick,
                fontSize = 15.sp,
                color = colors.headerfontcolor)
            Spacer(modifier = Modifier.width(5.dp))
            Text(click,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(destination) })
        }
    }
}