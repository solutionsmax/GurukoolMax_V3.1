package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.RetrieveStudentReservationBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveStudentReservationParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FleetBusRoutesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveStudentReservationUseCase @Inject constructor(
    private val fleetBusRoutesRepository: FleetBusRoutesRepository
) : UseCase<List<RetrieveStudentReservationBusRoutesItems>, RetrieveStudentReservationParams>() {
    override suspend fun run(params: RetrieveStudentReservationParams): Either<Failure, List<RetrieveStudentReservationBusRoutesItems>> {
        return fleetBusRoutesRepository.retrieveStudentReservationList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iPickupRouteID = params.iPickupRouteID,
                iDropRouteID = params.iDropRouteID,
                iStatusID = params.iStatusID
            )

    }
}