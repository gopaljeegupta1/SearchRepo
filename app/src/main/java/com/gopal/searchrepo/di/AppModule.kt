package com.gopal.searchrepo.di

import android.content.Context
import com.gopal.searchrepo.internal.Constants
import com.gopal.searchrepo.api.GitAPIService
import com.gopal.searchrepo.db.AppDatabase
import com.gopal.searchrepo.db.DatabaseDao
import com.gopal.searchrepo.db.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRepoApi(retrofit: Retrofit): GitAPIService =
        retrofit.create(GitAPIService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDatabaseDao(tempDb: AppDatabase) = tempDb.dbDao()

    @Provides
    fun provideDatabaseRepository(tempDbDao: DatabaseDao) = DatabaseRepository(tempDbDao)

}