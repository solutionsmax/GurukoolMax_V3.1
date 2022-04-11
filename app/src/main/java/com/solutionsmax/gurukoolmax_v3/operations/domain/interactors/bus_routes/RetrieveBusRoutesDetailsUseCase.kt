package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveBusRoutesDetailsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class RetrieveBusRoutesDetailsUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<FleetBusRouteList>, RetrieveBusRoutesDetailsParams>() {
    override suspend fun run(params: RetrieveBusRoutesDetailsParams): Either<Failure, List<FleetBusRouteList>> {
        return fleetBusRoutesRepository.retrieveBusRouteDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}