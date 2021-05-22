package com.gopal.searchrepo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gopal.searchrepo.data.model.Repo

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(results: Repo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllData(results: List<Repo>)

    @Query("SELECT * FROM mytable ORDER BY id ASC")
    fun getAllData(): LiveData<MutableList<Repo>>

    @Query("SELECT * FROM mytable WHERE id = :id")
    fun getDataById(id: Int): LiveData<Repo>

    @Query("SELECT count(*) FROM mytable")
    fun numberOfItemsInDB(): LiveData<Int>

    @Query("DELETE FROM mytable WHERE id=:id")
    fun deleteData(id: Int): Int

}