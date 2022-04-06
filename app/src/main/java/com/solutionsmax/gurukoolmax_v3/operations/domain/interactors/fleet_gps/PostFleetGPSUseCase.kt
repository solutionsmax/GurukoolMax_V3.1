package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.FleetPostGPSParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetGPSRepository
import javax.inject.Inject

class PostFleetGPSUseCase @Inject constructor(
    private val fleetGPSRepository: FleetGPSRepository
) : UseCase<Int, FleetPostGPSParams>() {
    override suspend fun run(params: FleetPostGPSParams): Either<Failure, Int> {
        return fleetGPSRepository.postFleetGPS(
            url = params.url,
            sAuthorization = params.sAuthorization,
            fleetGPSPostItem = params.fleetGPSPostItem
        )
    }
}