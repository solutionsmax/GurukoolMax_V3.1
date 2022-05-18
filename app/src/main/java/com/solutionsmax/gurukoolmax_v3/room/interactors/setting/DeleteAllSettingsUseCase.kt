package com.solutionsmax.gurukoolmax_v3.room.interactors.setting

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.repository.SettingsRepository
import javax.inject.Inject

class DeleteAllSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<Unit, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, Unit> {
        return Either.Right(settingsRepository.deleteAllSettings())
    }
}