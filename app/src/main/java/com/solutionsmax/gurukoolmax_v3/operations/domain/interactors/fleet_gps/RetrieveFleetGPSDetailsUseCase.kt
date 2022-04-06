package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.FleetGPSRetrieveDetailsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetGPSRepository
import javax.inject.Inject

class RetrieveFleetGPSDetailsUseCase @Inject constructor(
    private val fleetGPSRepository: FleetGPSRepository
) : UseCase<List<FleetGPSRetrieveItems>, FleetGPSRetrieveDetailsParams>() {
    override suspend fun run(params: FleetGPSRetrieveDetailsParams): Either<Failure, List<FleetGPSRetrieveItems>> {
        return fleetGPSRepository.retrieveFleetGPSDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}