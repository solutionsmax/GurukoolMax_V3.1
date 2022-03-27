package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface ErrorLogsApi {

    @POST
    suspend fun postErrorLogs(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postErrorLogsItems: PostErrorLogsItems
    ): Int
}