package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusStopsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class GetFleetBusStopsUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<FleetBusPickupPointsList>, FleetBusStopsParams>() {
    override suspend fun run(params: FleetBusStopsParams): Either<Failure, List<FleetBusPickupPointsList>> {
        return fleetBusRoutesRepository.retrieveBusStopsList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID
        )
    }
}