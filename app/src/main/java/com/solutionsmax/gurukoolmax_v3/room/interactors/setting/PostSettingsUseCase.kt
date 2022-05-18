package com.solutionsmax.gurukoolmax_v3.room.interactors.setting

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem
import com.solutionsmax.gurukoolmax_v3.room.repository.SettingsRepository
import javax.inject.Inject

class PostSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<Long, SettingsItem>() {
    override suspend fun run(params: SettingsItem): Either<Failure, Long> {
        return Either.Right(settingsRepository.insertSettings(params))
    }
}