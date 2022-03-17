package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusPickupScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import javax.inject.Inject

class GetFleetPickupScheduleUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<FleetPickupScheduleList>, FleetBusPickupScheduleParams>() {
    override suspend fun run(params: FleetBusPickupScheduleParams): Either<Failure, List<FleetPickupScheduleList>> {
        return fleetBusRoutesRepository.retrieveBusPickupSchedule(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iRouteID = params.iRouteID,
            iStatusID = params.iStatusID
        )
    }
}