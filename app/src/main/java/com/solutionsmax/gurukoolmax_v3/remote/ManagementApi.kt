package com.solutionsmax.gurukoolmax_v3.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface ManagementApi {

    @GET
    suspend fun validateOperationsLogin(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("sUsername") sUsername: String,
        @Query("sPassword") sPassword: String
    ): Int
}