package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostFleetBusStopParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusStopRepository
import javax.inject.Inject

class PostBusStopsUseCase @Inject constructor(
    private val fleetBusStopRepository: FleetBusStopRepository
) : UseCase<Int, PostFleetBusStopParams>() {
    override suspend fun run(params: PostFleetBusStopParams): Either<Failure, Int> {
        return fleetBusStopRepository.postFleetBusStops(
            url = params.url,
            sToken = params.sAuthorization,
            postFleetBusStopItems = params.postFleetBusStopItems
        )
    }
}