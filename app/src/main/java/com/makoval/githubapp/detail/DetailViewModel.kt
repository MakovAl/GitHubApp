package com.makoval.githubapp.detail

import androidx.lifecycle.*
import com.makoval.githubapp.network.ContentReadme
import com.makoval.githubapp.network.DetailRepositories
import com.makoval.githubapp.network.GitHubApi
import com.makoval.githubapp.network.Repositories
import kotlinx.coroutines.launch


class DetailViewModel(repositories: Repositories, token: String) : ViewModel() {

    private val _token = token


    private val _repositories = MutableLiveData<Repositories>()
    val repositories: LiveData<Repositories>
        get() = _repositories

    private val _detailRepositories = MutableLiveData<DetailRepositories>()
    val detailRepositories: LiveData<DetailRepositories>
        get() = _detailRepositories

    private val _contentReadme = MutableLiveData<ContentReadme>()
    val contentReadme: LiveData<ContentReadme>
        get() = _contentReadme


    init {
        _repositories.value = repositories
        getGitHubDetailRepositories()
    }

    private fun getGitHubDetailRepositories() {
        viewModelScope.launch {
            val reposUri = _repositories.value?.name.toString()
            val ownerUri = _repositories.value?.owner?.login.toString()
            _detailRepositories.value =
                GitHubApi.retrofitService.getDetailRepositories(ownerUri, reposUri, "token $_token")
            try {
                _contentReadme.value = GitHubApi.retrofitService.getReadmeRepositories(
                    ownerUri,
                    reposUri,
                    "token $_token"
                )
            } catch (e: Exception) {

            }
        }
    }
}
