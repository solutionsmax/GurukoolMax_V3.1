package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_STUDENT_BUS_ROUTE_FLEET_BUS_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_schedule.PostBusScheduleItem
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetBusScheduleRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun postBusSchedule(
        url: String,
        sAuthorization: String,
        postBusScheduleItem: PostBusScheduleItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postBusSchedule(
                url + MethodConstants.POST_FLEET_BUS_SCHEDULE,
                "${TokenConstants.BEARER} $sAuthorization",
                postBusScheduleItem
            )
        )

    suspend fun amendBusSchedule(
        url: String,
        sAuthorization: String,
        postBusScheduleItem: PostBusScheduleItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendBusSchedule(
                url + MethodConstants.AMEND_FLEET_BUS_SCHEDULE,
                "${TokenConstants.BEARER} $sAuthorization",
                postBusScheduleItem
            )
        )


    suspend fun checkDuplicateBusSchedule(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iStopID: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateBusSchedule(
                url + MethodConstants.CHECK_DUPLICATE_FLEET_BUS_SCHEDULE,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iRouteID, iStopID
            )
        )

    suspend fun retrieveBusPickupScheduleDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FleetPickupScheduleList>> =
        Either.Right(
            fleetApi.retrieveBusPickupScheduleDetails(
                url + MethodConstants.RETRIEVE_DETAILS_FLEET_BUS_SCHEDULE,
                "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveStudentBusRoute(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iSortID: Int,
        iStatusID: Int
    ): Either<Failure, List<FleetPickupScheduleList>> =
        Either.Right(
            fleetApi.retrieveStudentBusRoute(
                url + RETRIEVE_STUDENT_BUS_ROUTE_FLEET_BUS_SCHEDULE,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iRouteID,
                iSortID,
                iStatusID
            )
        )
}