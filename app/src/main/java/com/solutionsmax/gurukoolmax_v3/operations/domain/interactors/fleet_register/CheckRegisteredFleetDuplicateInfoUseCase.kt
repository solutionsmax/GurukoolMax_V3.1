package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetDuplicateCheckParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class CheckRegisteredFleetDuplicateInfoUseCase @Inject constructor(
    private val fleetRegisteredRepository: FleetRegistrationRepository
) : UseCase<Int, FleetDuplicateCheckParams>() {
    override suspend fun run(params: FleetDuplicateCheckParams): Either<Failure, Int> {
        return fleetRegisteredRepository.checkDuplicateFleetRegistration(
            params.url,
            params.sAuthorization,
            params.sRegistrationNumber
        )
    }
}