package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementSetStatus
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetMovementRepository
import javax.inject.Inject

class SetFleetMovementStatusUseCase @Inject constructor(
    private val fleetMovementRepository: FleetMovementRepository
) : UseCase<Int, FleetMovementSetStatus>() {
    override suspend fun run(params: FleetMovementSetStatus): Either<Failure, Int> {
        return fleetMovementRepository.setFleetMovementStatus(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}