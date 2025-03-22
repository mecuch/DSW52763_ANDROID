package com.example.dsw_52763_android.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff


object non_clickables {
    @Composable
    fun HeaderText(contents: String){
        Text(
            text = contents,
            color = colors.headerfontcolor,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
            )
    }

    @Composable
    fun StandardTextField(values: String, labelValues: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = values,
            onValueChange = onValueChange,
            label = { Text(labelValues) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun StandardPasswordTextField(values: String, onValueChange: (String) -> Unit, labelValues: String){
        var isPasswordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = values,
            onValueChange = onValueChange,
            label = { Text(labelValues) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (isPasswordVisible) "Ukryj hasło" else "Pokaż hasło"

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}