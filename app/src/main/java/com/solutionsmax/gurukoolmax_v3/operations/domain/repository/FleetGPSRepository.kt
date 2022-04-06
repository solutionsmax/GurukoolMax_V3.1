package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_FLEET_GPS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_FLEET_GPS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_FLEET_GPS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_GPS_BY_DATE_RANGE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_GPS_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_GPS_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_GPS_TOP_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSPostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetGPSRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun postFleetGPS(
        url: String,
        sAuthorization: String,
        fleetGPSPostItem: FleetGPSPostItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetGPS(
                url + POST_FLEET_GPS, "${TokenConstants.BEARER} $sAuthorization", fleetGPSPostItem
            )
        )

    suspend fun amendFleetGPS(
        url: String,
        sAuthorization: String,
        fleetGPSPostItem: FleetGPSPostItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendFleetGPS(
                url + AMEND_FLEET_GPS, "${TokenConstants.BEARER} $sAuthorization", fleetGPSPostItem
            )
        )

    suspend fun checkDuplicateFleetGpsCoordinates(
        url: String,
        sAuthorization: String,
        sAddress: String,
        sLongitude: String,
        sLatitude: String,
        sSyncDT: String,
        iRouteID: Int,
        iDriverID: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateFleetGpsCoordinates(
                url + CHECK_DUPLICATE_FLEET_GPS,
                "${TokenConstants.BEARER} $sAuthorization",
                sAddress,
                sLongitude,
                sLatitude,
                sSyncDT,
                iRouteID,
                iDriverID
            )
        )

    suspend fun retrieveFleetGPSDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FleetGPSRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFleetGPSDetails(
                url + RETRIEVE_FLEET_GPS_DETAILS,
                "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveFleetGPSList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iDriverID: Int
    ): Either<Failure, List<FleetGPSRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFleetGPSList(
                url + RETRIEVE_FLEET_GPS_LIST,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iRouteID, iDriverID
            )
        )

    suspend fun retrieveFleetGPSTopList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iDriverID: Int
    ): Either<Failure, List<FleetGPSRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFleetGPSTopList(
                url + RETRIEVE_FLEET_GPS_TOP_LIST,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iRouteID, iDriverID
            )
        )

    suspend fun retrieveFleetGPSByDateRangeList(
        url: String,
        sAuthorization: String,
        iRouteID: Int,
        iDriverID: Int,
        dFromDate: String,
        dToDate: String
    ): Either<Failure, List<FleetGPSRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFleetGPSByDateRangeList(
                url + RETRIEVE_FLEET_GPS_BY_DATE_RANGE,
                "${TokenConstants.BEARER} $sAuthorization",
                iRouteID, iDriverID, dFromDate, dToDate
            )
        )
}