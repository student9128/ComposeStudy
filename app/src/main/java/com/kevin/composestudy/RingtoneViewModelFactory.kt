package com.kevin.composestudy

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RingtoneViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RingtoneViewModel::class.java)) {
            return RingtoneViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}