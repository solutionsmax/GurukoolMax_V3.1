package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementFetchWorkflowStatus
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetMovementRepository
import javax.inject.Inject

class FetchFleetMovementClosingRangeUseCase @Inject constructor(
    private val fleetMovementRepository: FleetMovementRepository
) : UseCase<Int, FleetMovementFetchWorkflowStatus>() {
    override suspend fun run(params: FleetMovementFetchWorkflowStatus): Either<Failure, Int> {
        return fleetMovementRepository.fetchClosingRangeFleetMovement(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iVehicleID = params.iVehicleID,
            iWorkflowStatusID = params.iWorkflowStatusID
        )
    }
}