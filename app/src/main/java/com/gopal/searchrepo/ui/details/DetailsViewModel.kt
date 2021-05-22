package com.gopal.searchrepo.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gopal.searchrepo.data.GitRepository
import com.gopal.searchrepo.data.model.Owner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val repository: GitRepository
) : ViewModel() {

    private val _res = MutableLiveData<List<Owner>>()
    val res: LiveData<List<Owner>>
        get() = _res

    fun getContributors(ownerName: String, repoName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getContributors(ownerName, repoName)
        }
    }

    companion object {
        private const val DEFAULT_QUERY = "language:Kotlin"
    }

}