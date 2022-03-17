package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPopulateList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementRetrieveList
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetMovementRepository
import javax.inject.Inject

class PopulateFleetMovementListUseCase @Inject constructor(
    private val fleetMovementRepository: FleetMovementRepository
) : UseCase<List<FleetMovementPopulateList>, FleetMovementRetrieveList>() {
    override suspend fun run(params: FleetMovementRetrieveList): Either<Failure, List<FleetMovementPopulateList>> {
        return fleetMovementRepository.populateFleetMovementList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iWorkflowStatusID = params.iWorkflowStatusID
        )
    }
}