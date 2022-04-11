package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class PostBusRoutesUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<Int, PostBusRoutesParams>() {
    override suspend fun run(params: PostBusRoutesParams): Either<Failure, Int> {
        return fleetBusRoutesRepository.postBusRoutesInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postBusRouteItem = params.postBusRouteItem
        )
    }
}