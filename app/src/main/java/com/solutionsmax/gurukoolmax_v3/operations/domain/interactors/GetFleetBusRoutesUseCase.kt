package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class GetFleetBusRoutesUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<FleetBusRouteList>, FleetBusRoutesParams>() {
    override suspend fun run(params: FleetBusRoutesParams): Either<Failure, List<FleetBusRouteList>> {
        return fleetBusRoutesRepository.retrieveBusRoutesList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID
        )
    }
}