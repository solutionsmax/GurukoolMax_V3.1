package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.ErrorLogsApi
import javax.inject.Inject

class ErrorLogsRepository @Inject constructor(private val errorLogsApi: ErrorLogsApi) {

    suspend fun postErrorLogs(
        url: String,
        sAuthorization: String,
        postErrorLogsItems: PostErrorLogsItems
    ): Either<Failure, Int> =
        Either.Right(
            errorLogsApi.postErrorLogs(
                url, "${TokenConstants.BEARER} $sAuthorization", postErrorLogsItems
            )
        )
}