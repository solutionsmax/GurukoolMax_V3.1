package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.DuplicateBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class CheckDuplicateBusRouteUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<Int, DuplicateBusRoutesParams>() {
    override suspend fun run(params: DuplicateBusRoutesParams): Either<Failure, Int> {
        return fleetBusRoutesRepository.checkDuplicateBusRoute(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            sRouteName = params.sRouteName
        )
    }
}