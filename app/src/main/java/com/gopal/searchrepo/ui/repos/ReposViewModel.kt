package com.gopal.searchrepo.ui.repos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gopal.searchrepo.data.GitRepository

class ReposViewModel @ViewModelInject constructor (
    private val repository: GitRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val repos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchRepos(query: String) {
        currentQuery.value = query
    }

    companion object {
//        private const val DEFAULT_QUERY = "language:Kotlin"
        private const val DEFAULT_QUERY = "Kotlin"
    }

}