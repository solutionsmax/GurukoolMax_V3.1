package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FETCH_SEMESTER_FORMAT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.SettingsApi
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsApi: SettingsApi
) {

    suspend fun fetchSemesterFormatInfo(
        url: String,
        sToken: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            settingsApi.fetchSemesterFormatInfo(
                "$url$FETCH_SEMESTER_FORMAT_INFO", "${TokenConstants.BEARER} $sToken", id
            )
        )
}