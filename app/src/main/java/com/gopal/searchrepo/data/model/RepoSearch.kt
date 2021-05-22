package com.gopal.searchrepo.data.model

import com.google.gson.annotations.SerializedName

data class RepoSearch(

    @SerializedName("total_count")
    val total: Int = 0,

    @SerializedName("items")
    val repos: List<Repo> = emptyList(),

    val nextPage: Int? = null
)