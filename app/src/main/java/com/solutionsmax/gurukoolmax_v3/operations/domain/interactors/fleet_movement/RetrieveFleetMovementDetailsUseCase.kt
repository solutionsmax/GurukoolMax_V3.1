package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementRetrieveDetails
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetMovementRepository
import javax.inject.Inject

class RetrieveFleetMovementDetailsUseCase @Inject constructor(
    private val fleetMovementRepository: FleetMovementRepository
) : UseCase<List<FleetMovementRetrieveItem>, FleetMovementRetrieveDetails>() {
    override suspend fun run(params: FleetMovementRetrieveDetails): Either<Failure, List<FleetMovementRetrieveItem>> {
        return fleetMovementRepository.retrieveFleetMovementDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}