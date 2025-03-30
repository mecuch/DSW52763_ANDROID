package com.example.dsw_52763_android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.utils.routes

@Composable
fun RegisterPage(navController: NavController){
    var username by remember { mutableStateOf("") }
    var emails by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            clickables.ClickableBack(navController)
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
            Spacer(modifier = Modifier.height(10.dp))
            non_clickables.StandardTextField(
                values = username,
                labelValues = "Full name",
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            non_clickables.StandardTextField(
                values = emails,
                labelValues = "E-mail",
                onValueChange = { emails = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            non_clickables.StandardPasswordTextField(
                values = password,
                labelValues = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            non_clickables.StandardPasswordTextField(
                values = confirmpassword,
                labelValues = "Confirm Password",
                onValueChange = { confirmpassword = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            clickables.ClickableButton(navController, routes.loginPage, "Sign Up")
            Spacer(
                modifier = Modifier.height(100.dp))
            clickables.ClickableText(navController,
                "Already have a account?",
                "Sign In",
                routes.loginPage)
        }
    }
}