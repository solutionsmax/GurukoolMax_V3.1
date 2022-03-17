package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetRegisterPostParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class PostRegisteredFleetPhotoUseCase @Inject constructor(
    private val fleetRegisteredRepository: FleetRegistrationRepository
) : UseCase<Int, FleetRegisterPostParams>() {
    override suspend fun run(params: FleetRegisterPostParams): Either<Failure, Int> {
        return fleetRegisteredRepository.postFleetPhoto(
            params.url,
            params.sAuthorization,
            params.fleetRegistrationPostInfoItem
        )
    }
}