package com.trex.ainote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AIViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AIViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AIViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}