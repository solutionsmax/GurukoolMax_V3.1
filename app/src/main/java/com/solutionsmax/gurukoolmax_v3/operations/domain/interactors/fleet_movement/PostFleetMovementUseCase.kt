package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementPostParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetMovementRepository
import javax.inject.Inject

class PostFleetMovementUseCase @Inject constructor(
    private val fleetMovementRepository: FleetMovementRepository
) : UseCase<Int, FleetMovementPostParams>() {
    override suspend fun run(params: FleetMovementPostParams): Either<Failure, Int> {
        return fleetMovementRepository.postFleetMovementInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            fleetMovementPostInfoItem = params.fleetMovementPostInfoItem
        )
    }
}