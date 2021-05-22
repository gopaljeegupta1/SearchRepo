package com.gopal.searchrepo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gopal.searchrepo.api.GitAPIService
import com.gopal.searchrepo.db.DatabaseRepository
import com.gopal.searchrepo.data.model.Repo
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class GitPagingSource(
    private val gitAPIService: GitAPIService,
    private val query: String,
    private val dbRepository: DatabaseRepository
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            /*getRepo*/
            val response = gitAPIService.getRepos(query, position, params.loadSize)
            val repos = response.repos

            /*insertRepoIntoDb*/
            dbRepository.insertAllData(repos)

            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        TODO("Not yet implemented")
    }

}