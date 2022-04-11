package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FetchBusRouteNameParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class FetchBusRouteNameUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<Int, FetchBusRouteNameParams>() {
    override suspend fun run(params: FetchBusRouteNameParams): Either<Failure, Int> {
        return fleetBusRoutesRepository.fetchBusRouteName(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}