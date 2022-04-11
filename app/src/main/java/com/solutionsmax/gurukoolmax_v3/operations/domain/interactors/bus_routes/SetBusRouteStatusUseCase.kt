package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.BusRouteStatusParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class SetBusRouteStatusUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<Int, BusRouteStatusParams>() {
    override suspend fun run(params: BusRouteStatusParams): Either<Failure, Int> {
        return fleetBusRoutesRepository.setBusRouteStatus(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}