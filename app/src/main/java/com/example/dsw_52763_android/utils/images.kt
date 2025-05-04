package com.example.dsw_52763_android.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dsw_52763_android.R

object images {
    @Composable
    fun Logos() {
        Image(
            painter = painterResource(id = R.drawable.logo_main),
            contentDescription = "logo",
            modifier = Modifier
                .size(140.dp)
                .padding(vertical = 10.dp)
        )
    }
    @Composable
    fun Ellipse() {
        Image(
            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = 0.dp, end = 0.dp)
                .size(140.dp)
        )
    }

}