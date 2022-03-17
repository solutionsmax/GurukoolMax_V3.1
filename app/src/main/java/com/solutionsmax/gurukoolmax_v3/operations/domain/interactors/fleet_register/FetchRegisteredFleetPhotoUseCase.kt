package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FetchRegisteredFleetPhoto
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class FetchRegisteredFleetPhotoUseCase @Inject constructor(
    private val fleetRegistrationRepository: FleetRegistrationRepository
) : UseCase<String, FetchRegisteredFleetPhoto>() {
    override suspend fun run(params: FetchRegisteredFleetPhoto): Either<Failure, String> {
        return fleetRegistrationRepository.fetchRegisteredFleetPhoto(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iVehicleID = params.iVehicleID
        )
    }
}