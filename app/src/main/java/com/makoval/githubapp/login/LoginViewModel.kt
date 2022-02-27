package com.makoval.githubapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makoval.githubapp.network.GitHubApi
import com.makoval.githubapp.network.Repositories
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ReposApiStatus { LOADING, ERROR, DONE }

class LoginViewModel(token: String) : ViewModel() {

    private val _status = MutableLiveData<ReposApiStatus>()
    val status: LiveData<ReposApiStatus> = _status


    private val _repositories = MutableLiveData<List<Repositories>>()
    val repositories: LiveData<List<Repositories>> = _repositories

    init {
        getGitHubRepositories(token)
    }

    private fun getGitHubRepositories(token: String) {
        viewModelScope.launch {
            _status.value = ReposApiStatus.LOADING
            try {
                _repositories.value = GitHubApi.retrofitService.getRepositories("token $token")
                _status.value = ReposApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ReposApiStatus.ERROR
                _repositories.value = ArrayList()
            }
        }
    }
}
