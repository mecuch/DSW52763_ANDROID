package com.example.dsw_52763_android.utils

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
}