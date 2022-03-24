package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.PopulateFleetBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PopulateFleetBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class PopulateFleetsRoutesUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<MutableList<PopulateFleetBusRoutesItems>, PopulateFleetBusRoutesParams>() {
    override suspend fun run(params: PopulateFleetBusRoutesParams): Either<Failure, MutableList<PopulateFleetBusRoutesItems>> {
        return fleetBusRoutesRepository.populateFleetBusRoutes(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iStatusID = params.iStatusID
        )
    }
}