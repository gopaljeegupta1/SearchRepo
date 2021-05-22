package com.gopal.searchrepo.db

import com.gopal.searchrepo.data.model.Repo
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val dbDao: DatabaseDao
) {

    suspend fun insertData(results: Repo) = dbDao.insertData(results)

    suspend fun insertAllData(results: List<Repo>) = dbDao.insertAllData(results)

    suspend fun getAllData() = dbDao.getAllData()

    fun numberOfItemsInDB() = dbDao.numberOfItemsInDB()

    suspend fun deleteData(id: Int) = dbDao.deleteData(id)

}