package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.FleetGPSDuplicateParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetGPSRepository
import javax.inject.Inject

class CheckDuplicateFleetGPSUseCase @Inject constructor(
    private val fleetGPSRepository: FleetGPSRepository
) : UseCase<Int, FleetGPSDuplicateParams>() {
    override suspend fun run(params: FleetGPSDuplicateParams): Either<Failure, Int> {
        return fleetGPSRepository.checkDuplicateFleetGpsCoordinates(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sAddress = params.sAddress,
            sLatitude = params.sLatitude,
            sLongitude = params.sLongitude,
            sSyncDT = params.sSyncDT,
            iRouteID = params.iRouteID,
            iDriverID = params.iDriverID
        )
    }
}