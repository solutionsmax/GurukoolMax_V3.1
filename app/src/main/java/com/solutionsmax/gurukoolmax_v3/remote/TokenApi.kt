package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.core.data.LicenseEntity
import com.solutionsmax.gurukoolmax_v3.core.data.token.TokenEntity
import okhttp3.RequestBody
import retrofit2.http.*

interface TokenApi {

    @POST("SolutionsMaxToken")
    suspend fun getToken(
        @Body body: RequestBody
    ): TokenEntity


    @GET("LicenseSR/RetrieveListBySiteCode")
    suspend fun retrieveURLSBySiteCode(
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("SiteCode") SiteCode: String
    ): List<LicenseEntity>
}