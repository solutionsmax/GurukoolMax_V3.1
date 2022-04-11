package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.SetFleetBusStopStatusParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusStopRepository
import javax.inject.Inject

class SetFleetBusRouteStatusUseCase @Inject constructor(
    private val fleetBusStopRepository: FleetBusStopRepository
) : UseCase<Int, SetFleetBusStopStatusParams>() {
    override suspend fun run(params: SetFleetBusStopStatusParams): Either<Failure, Int> {
        return fleetBusStopRepository.setFleetBusStopStatus(
            url = params.url,
            sToken = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}