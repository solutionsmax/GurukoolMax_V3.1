package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.CheckDuplicateFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusScheduleRepository
import javax.inject.Inject

class CheckDuplicateBusScheduleUseCase @Inject constructor(
    private val fleetBusScheduleRepository: FleetBusScheduleRepository
) : UseCase<Int, CheckDuplicateFleetBusScheduleParams>() {
    override suspend fun run(params: CheckDuplicateFleetBusScheduleParams): Either<Failure, Int> {
        return fleetBusScheduleRepository.checkDuplicateBusSchedule(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iRouteID = params.iRouteID,
            iStopID = params.iStopID
        )
    }
}