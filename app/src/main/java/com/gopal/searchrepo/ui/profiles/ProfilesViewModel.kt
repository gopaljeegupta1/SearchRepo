package com.gopal.searchrepo.ui.profiles

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.gopal.searchrepo.api.GitRepository

class ProfilesViewModel @ViewModelInject constructor(
    private val repository: GitRepository
) : ViewModel() {

    private val _res = MutableLiveData<String>()
    val res: LiveData<String>
        get() = _res

    val repos = _res.switchMap { queryString ->
        repository.getRepositoryResults(queryString).cachedIn(viewModelScope)
    }

    fun searchContributor(ownerName: String) {
        _res.value = ownerName
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