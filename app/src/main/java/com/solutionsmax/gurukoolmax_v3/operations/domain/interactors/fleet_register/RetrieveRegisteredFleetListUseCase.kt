package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetRegisteredListParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class RetrieveRegisteredFleetListUseCase @Inject constructor(
    private val fleetRegistrationRepository: FleetRegistrationRepository
) : UseCase<List<FleetRegisterRetrieveListItem>, FleetRegisteredListParams>() {
    override suspend fun run(params: FleetRegisteredListParams): Either<Failure, List<FleetRegisterRetrieveListItem>> {
        return fleetRegistrationRepository.retrieveRegisteredFleetList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iWorkflowStatusID = params.iWorkflowStatusID
        )
    }
}