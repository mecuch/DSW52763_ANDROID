package com.example.dsw_52763_android.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PassManModelFactory(
    private val context: Context,
    private val userId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PassManViewModel(context.applicationContext as Application, userId) as T
    }
}