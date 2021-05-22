package com.gopal.searchrepo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gopal.searchrepo.api.GitAPIService
import com.gopal.searchrepo.data.model.Owner
import com.gopal.searchrepo.db.DatabaseRepository
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ContributorPagingSource(
    private val gitAPIService: GitAPIService,
    private val listData: List<String>,
    private val dbRepository: DatabaseRepository
) : PagingSource<Int, Owner>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Owner> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            /*getRepo*/
            val response = gitAPIService.getContributors(listData!!.get(0), listData!!.get(1))
            val owner = response

            /*insertRepoIntoDb*/
//            dbRepository.insertAllData(repos)

            LoadResult.Page(
                data = owner,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (owner.isEmpty()) null else position + 1
            )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Owner>): Int? {
        TODO("Not yet implemented")
    }

}