package com.solutionsmax.gurukoolmax_v3.room.interactors.setting

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem
import com.solutionsmax.gurukoolmax_v3.room.repository.SettingsRepository
import javax.inject.Inject

class AmendSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<Unit, SettingsItem>() {
    override suspend fun run(params: SettingsItem): Either<Failure, Unit> {
        return Either.Right(settingsRepository.amendSettings(params))
    }
}