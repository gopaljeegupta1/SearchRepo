package com.gopal.searchrepo.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.gopal.searchrepo.api.GitRepository

class DetailsViewModel @ViewModelInject constructor(
    private val repository: GitRepository
) : ViewModel() {

    private val _res = MutableLiveData<List<String>>()
    val res: LiveData<List<String>>
        get() = _res

    val repos = _res.switchMap { queryString ->
        repository.getContributorResults(queryString).cachedIn(viewModelScope)
    }

    fun searchContributor(ownerName: String, repoName: String) {
        var list: List<String> = mutableListOf(ownerName, repoName)
        _res.value = list
    }


    /*private val _res = MutableLiveData<List<Owner>>()
   val res: LiveData<List<Owner>>
       get() = _res

   fun getContributors(ownerName: String, repoName: String) {
       viewModelScope.launch(Dispatchers.IO) {
           val result = repository.getContributors(ownerName, repoName)
           _res.postValue(result)
       }
   }*/


}