package com.example.dsw_52763_android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dsw_52763_android.R
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.non_clickables
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomePage( navController: NavController) {

    val email = FirebaseAuth.getInstance().currentUser?.email ?: "user"
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            non_clickables.UserLoginText(email)
            clickables.ClickableLogout(navController)
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Column {
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
        ) {
            non_clickables.HeaderText("Welcome in Lobby!")
            Spacer(modifier = Modifier.height(50.dp))
            clickables.CustomImageButton(R.drawable.notes, navController, "notesPage/$userId", "Notes/To do List Manager")
            Spacer(modifier = Modifier.height(20.dp))
            clickables.CustomImageButton(R.drawable.pass, navController, "passmanPage/$userId", "Password Manager")



        }

    }
}
