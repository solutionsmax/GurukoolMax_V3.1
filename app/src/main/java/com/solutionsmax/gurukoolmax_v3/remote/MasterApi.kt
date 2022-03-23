package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface MasterApi {

    @GET
    suspend fun populateMasters(
        @Url string: String,
        @Header("Authorization") sAuthorization: String,
        @Query("sTableName") sTableName: String
    ): MutableList<PopulateMasterListItem>
}