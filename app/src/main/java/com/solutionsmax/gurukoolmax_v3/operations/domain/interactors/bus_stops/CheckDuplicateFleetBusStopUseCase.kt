package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.CheckDuplicateBusStopParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusStopRepository
import javax.inject.Inject

class CheckDuplicateFleetBusStopUseCase @Inject constructor(
    private val fleetBusStopRepository: FleetBusStopRepository
) : UseCase<Int, CheckDuplicateBusStopParams>() {
    override suspend fun run(params: CheckDuplicateBusStopParams): Either<Failure, Int> {
        return fleetBusStopRepository.checkDuplicateFleetBusStop(
            url = params.url,
            sToken = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iRouteID = params.iRouteID,
            sStopName = params.sStopName
        )
    }
}