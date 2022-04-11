package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveBusStopsDetailsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusStopRepository
import javax.inject.Inject

class RetrieveBusStopDetailsUseCase @Inject constructor(
    private val fleetBusStopRepository: FleetBusStopRepository
) : UseCase<List<FleetBusPickupPointsList>, RetrieveBusStopsDetailsParams>() {
    override suspend fun run(params: RetrieveBusStopsDetailsParams): Either<Failure, List<FleetBusPickupPointsList>> {
        return fleetBusStopRepository.retrieveBusStopDetails(
            url = params.url,
            sToken = params.sAuthorization,
            id = params.id
        )
    }
}