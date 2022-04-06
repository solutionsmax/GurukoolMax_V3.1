package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.FleetGPSRetrieveListByDateRangeParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetGPSRepository
import javax.inject.Inject

class RetrieveFleetGPSByDateRangeUseCase @Inject constructor(
    private val fleetGPSRepository: FleetGPSRepository
) : UseCase<List<FleetGPSRetrieveItems>, FleetGPSRetrieveListByDateRangeParams>() {
    override suspend fun run(params: FleetGPSRetrieveListByDateRangeParams): Either<Failure, List<FleetGPSRetrieveItems>> {
        return fleetGPSRepository.retrieveFleetGPSByDateRangeList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iRouteID = params.iRouteID,
            iDriverID = params.iDriverID,
            dFromDate = params.dFromDate,
            dToDate = params.dToDate
        )
    }
}