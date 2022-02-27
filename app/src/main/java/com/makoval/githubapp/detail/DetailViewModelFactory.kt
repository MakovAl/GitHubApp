package com.makoval.githubapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.makoval.githubapp.network.Repositories

class DetailViewModelFactory(private val repositories: Repositories, private val token: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repositories, token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
