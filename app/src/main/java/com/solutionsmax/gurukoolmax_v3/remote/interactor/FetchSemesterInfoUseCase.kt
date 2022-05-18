package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.entity.SettingsParams
import com.solutionsmax.gurukoolmax_v3.remote.repository.SettingsRepository
import javax.inject.Inject

class FetchSemesterInfoUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<Int, SettingsParams>() {
    override suspend fun run(params: SettingsParams): Either<Failure, Int> {
        return settingsRepository.fetchSemesterFormatInfo(
            url = params.url,
            sToken = params.sAuthorization,
            id = params.id
        )
    }
}