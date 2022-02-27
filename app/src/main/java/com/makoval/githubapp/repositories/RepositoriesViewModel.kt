package com.makoval.githubapp.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makoval.githubapp.network.GitHubApi
import com.makoval.githubapp.network.Repositories
import kotlinx.coroutines.launch


class RepositoriesViewModel(token: String) : ViewModel() {

    private val _navigateToSelectedRepositories = MutableLiveData<Repositories?>()
    val navigateToSelectedRepositories: LiveData<Repositories?>
        get() = _navigateToSelectedRepositories



    private val _repositories = MutableLiveData<List<Repositories>>()
    val repositories: LiveData<List<Repositories>> = _repositories

    init {
        getGitHubRepositories(token)
    }

    private fun getGitHubRepositories(token: String) {
        viewModelScope.launch {
                _repositories.value = GitHubApi.retrofitService.getRepositories("token $token")
        }
    }

    fun displayRepositoryDetails(repositories: Repositories) {
        _navigateToSelectedRepositories.value = repositories
    }

    fun displayRepositoryDetailsComplete() {
        _navigateToSelectedRepositories.value = null
    }
}