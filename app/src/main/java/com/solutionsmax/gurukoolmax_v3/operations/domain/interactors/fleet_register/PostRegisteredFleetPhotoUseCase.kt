package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.PostFleetPhoto
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetRegistrationRepository
import javax.inject.Inject

class PostRegisteredFleetPhotoUseCase @Inject constructor(
    private val fleetRegisteredRepository: FleetRegistrationRepository
) : UseCase<Int, PostFleetPhoto>() {
    override suspend fun run(params: PostFleetPhoto): Either<Failure, Int> {
        return fleetRegisteredRepository.postFleetPhoto(
            params.url,
            params.sAuthorization,
            params.fleetPostPhotoItem
        )
    }
}