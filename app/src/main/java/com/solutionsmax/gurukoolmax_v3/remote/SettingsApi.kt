package com.solutionsmax.gurukoolmax_v3.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface SettingsApi {

    @GET
    suspend fun fetchSemesterFormatInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): Int
}