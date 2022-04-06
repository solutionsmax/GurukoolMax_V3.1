package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.FleetGPSRetrieveListParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetGPSRepository
import javax.inject.Inject

class RetrieveFleetGPSListUseCase @Inject constructor(
    private val fleetGPSRepository: FleetGPSRepository
) : UseCase<List<FleetGPSRetrieveItems>, FleetGPSRetrieveListParams>() {
    override suspend fun run(params: FleetGPSRetrieveListParams): Either<Failure, List<FleetGPSRetrieveItems>> {
        return fleetGPSRepository.retrieveFleetGPSList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iRouteID = params.iRouteID,
            iDriverID = params.iDriverID
        )
    }
}