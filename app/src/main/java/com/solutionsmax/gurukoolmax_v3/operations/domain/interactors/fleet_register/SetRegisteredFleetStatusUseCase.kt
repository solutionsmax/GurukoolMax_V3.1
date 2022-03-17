package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.SetRegisteredFleetStatus
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class SetRegisteredFleetStatusUseCase @Inject constructor(
    private val fleetRegisteredRepository: FleetRegistrationRepository
) : UseCase<Int, SetRegisteredFleetStatus>() {
    override suspend fun run(params: SetRegisteredFleetStatus): Either<Failure, Int> {
        return fleetRegisteredRepository.setRegisteredFleetStatus(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}