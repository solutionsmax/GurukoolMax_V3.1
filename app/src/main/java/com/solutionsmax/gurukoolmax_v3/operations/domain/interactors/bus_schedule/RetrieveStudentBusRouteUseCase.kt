package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveStudentBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusScheduleRepository
import javax.inject.Inject

class RetrieveStudentBusRouteUseCase @Inject constructor(
    private val fleetBusScheduleRepository: FleetBusScheduleRepository
) : UseCase<List<FleetPickupScheduleList>, RetrieveStudentBusScheduleParams>() {
    override suspend fun run(params: RetrieveStudentBusScheduleParams): Either<Failure, List<FleetPickupScheduleList>> {
        return fleetBusScheduleRepository.retrieveStudentBusRoute(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iRouteID = params.iRouteID,
            iSortID = params.iSortID,
            iStatusID = params.iStatusID
        )
    }
}