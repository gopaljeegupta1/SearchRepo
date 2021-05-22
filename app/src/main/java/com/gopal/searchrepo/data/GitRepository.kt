package com.gopal.searchrepo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.gopal.searchrepo.api.GitAPIService
import com.gopal.searchrepo.db.DatabaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepository @Inject constructor(
    private val gitAPIService: GitAPIService,
    private val dbRepository: DatabaseRepository
) {
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(initialLoadSize = 10, pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { GitPagingSource(gitAPIService, query, dbRepository) }
        ).liveData


    suspend fun getContributors(ownerName: String, repoName: String) {
        gitAPIService.getContributors(ownerName, repoName)
    }

}

