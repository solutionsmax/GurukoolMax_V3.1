package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetRegisteredListParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class PopulateRegisteredFleetUseCase @Inject constructor(
    private val fleetRegistrationRepository: FleetRegistrationRepository
) : UseCase<MutableList<PopulateRegisteredFleetList>, FleetRegisteredListParams>() {
    override suspend fun run(params: FleetRegisteredListParams): Either<Failure, MutableList<PopulateRegisteredFleetList>> {
        return fleetRegistrationRepository.populateRegisteredFleetList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iWorkflowStatusID = params.iWorkflowStatusID
        )
    }
}