package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusScheduleRepository
import javax.inject.Inject

class AmendBusScheduleUseCase @Inject constructor(
    private val fleetBusScheduleRepository: FleetBusScheduleRepository
) : UseCase<Int, PostFleetBusScheduleParams>() {
    override suspend fun run(params: PostFleetBusScheduleParams): Either<Failure, Int> {
        return fleetBusScheduleRepository.amendBusSchedule(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postBusScheduleItem = params.postBusScheduleItem
        )
    }
}