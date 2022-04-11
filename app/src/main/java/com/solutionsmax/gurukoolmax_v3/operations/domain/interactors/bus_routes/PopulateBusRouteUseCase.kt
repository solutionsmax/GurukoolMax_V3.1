package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PopulateBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class PopulateBusRouteUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<PopulateBusRoutesItems>, PopulateBusRoutesParams>() {
    override suspend fun run(params: PopulateBusRoutesParams): Either<Failure, List<PopulateBusRoutesItems>> {
        return fleetBusRoutesRepository.populateBusRouteName(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iStatusID = params.iStatusID
        )
    }
}