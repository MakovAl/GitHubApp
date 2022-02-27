package com.makoval.githubapp.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.makoval.githubapp.network.Repositories

class RepositoriesViewModelFactory(private val token: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
