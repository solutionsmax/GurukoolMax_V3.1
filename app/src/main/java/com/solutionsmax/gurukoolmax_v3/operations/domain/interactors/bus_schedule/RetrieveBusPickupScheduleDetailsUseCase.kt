package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveDetailsFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusScheduleRepository
import javax.inject.Inject

class RetrieveBusPickupScheduleDetailsUseCase @Inject constructor(
    private val fleetBusScheduleRepository: FleetBusScheduleRepository
) : UseCase<List<FleetPickupScheduleList>, RetrieveDetailsFleetBusScheduleParams>() {
    override suspend fun run(params: RetrieveDetailsFleetBusScheduleParams): Either<Failure, List<FleetPickupScheduleList>> {
        return fleetBusScheduleRepository.retrieveBusPickupScheduleDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}