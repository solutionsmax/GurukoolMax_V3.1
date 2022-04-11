package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PopulateBusStopsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusStopRepository
import javax.inject.Inject

class PopulateFleetBusStopUseCase @Inject constructor(
    private val fleetBusStopRepository: FleetBusStopRepository
) : UseCase<List<PopulateFleetBusStopItems>, PopulateBusStopsParams>() {
    override suspend fun run(params: PopulateBusStopsParams): Either<Failure, List<PopulateFleetBusStopItems>> {
        return fleetBusStopRepository.populateFleetBusStopList(
            url = params.url,
            sToken = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iRouteID = params.iRouteID,
            iEditMode = params.iEditMode,
            iStatusID = params.iStatusID
        )
    }
}