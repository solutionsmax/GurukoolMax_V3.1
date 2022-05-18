package com.solutionsmax.gurukoolmax_v3.room.interactors.setting

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem
import com.solutionsmax.gurukoolmax_v3.room.repository.SettingsRepository
import javax.inject.Inject

class RetrieveSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<List<SettingsItem>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, List<SettingsItem>> =
        Either.Right(settingsRepository.retrieveSettings())
}