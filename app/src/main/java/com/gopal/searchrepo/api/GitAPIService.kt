package com.gopal.searchrepo.api

import com.gopal.searchrepo.data.model.Repo
import com.gopal.searchrepo.data.model.Owner
import com.gopal.searchrepo.data.model.RepoSearch
import com.gopal.searchrepo.internal.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitAPIService {

    @GET(Constants.GET_REPOS)
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearch

    @GET(Constants.GET_CONTRIBUTORS)
    suspend fun getContributors(
        @Path("ownerName") ownerName: String,
        @Path("repoName") repoName: String
    ): List<Owner>

    @GET(Constants.GET_REPOS_BY_NAME)
    suspend fun getReposByName(@Path("ownerName") ownerName: String): List<Repo>
}