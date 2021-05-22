package com.gopal.searchrepo.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.gopal.searchrepo.data.ContributorPagingSource
import com.gopal.searchrepo.data.GitPagingSource
import com.gopal.searchrepo.data.RepositoryPagingSource
import com.gopal.searchrepo.data.model.Owner
import com.gopal.searchrepo.db.DatabaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepository @Inject constructor(
    private val gitAPIService: GitAPIService,
    private val dbRepository: DatabaseRepository
) {

    /*get search result*/
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(initialLoadSize = 10, pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { GitPagingSource(gitAPIService, query, dbRepository) }
        ).liveData

    /*get contributor result*/
    fun getContributorResults(ownerName: List<String>) =
        Pager(
            config = PagingConfig(initialLoadSize = 10, pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                ContributorPagingSource(
                    gitAPIService,
                    ownerName,
                    dbRepository
                )
            }
        ).liveData

    /*get contributor result*/
    fun getRepositoryResults(ownerName: String) =
        Pager(
            config = PagingConfig(initialLoadSize = 10, pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                RepositoryPagingSource(
                    gitAPIService,
                    ownerName,
                    dbRepository
                )
            }
        ).liveData

    /*suspend fun getContributors(ownerName: String, repoName: String): List<Owner> {
        val result = gitAPIService.getContributors(ownerName, repoName)
        return result
    }*/

}

