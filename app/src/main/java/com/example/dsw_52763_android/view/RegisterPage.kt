package com.example.dsw_52763_android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.images
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.AuthState
import com.example.dsw_52763_android.view_model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterPage(navController: NavController, authViewModel : AuthViewModel){
    var username by remember { mutableStateOf("") }
    var emails by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
    var showValidationError1 by remember { mutableStateOf(false) }
    var showValidationError2 by remember { mutableStateOf(false) }
    var validationErrorMessage by remember { mutableStateOf("") }
    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                navController.navigate("homePage/$userId") {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .width(360.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            clickables.ClickableBack(navController, routes.loginPage)
            Spacer(modifier = Modifier.weight(1f))
            images.Ellipse()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            non_clickables.HeaderText("Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            non_clickables.StandardTextField(
                values = username,
                labelValues = "Full name",
                onValueChange = { username = it },
                verticalSize = 65
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardTextField(
                values = emails,
                labelValues = "E-mail",
                onValueChange = { emails = it },
                verticalSize = 65
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = password,
                labelValues = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = confirmpassword,
                labelValues = "Confirm Password",
                onValueChange = { confirmpassword = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            clickables.FuncClickableButton({
                when {
                    username.isBlank() || emails.isBlank() || password.isBlank() || confirmpassword.isBlank() -> {
                        validationErrorMessage = "All fields are required!"
                        showValidationError1 = true
                    }
                    password != confirmpassword -> {
                        validationErrorMessage = "Passwords do not match!"
                        showValidationError2 = true
                    }
                    else -> {
                        authViewModel.signup(emails, password)
                    }
                }
            }, "Sign Up")

            if (authState is AuthState.Error) {
                val error = (authState as AuthState.Error).message
                non_clickables.WarningErrorText(error)
            }
            if (showValidationError1 || showValidationError2) {
                non_clickables.WarningErrorText(validationErrorMessage)
            }
            Spacer(
                modifier = Modifier.height(100.dp))
            clickables.ClickableText(navController,
                "Already have a account?",
                "Sign In",
                routes.loginPage)
        }
    }
}