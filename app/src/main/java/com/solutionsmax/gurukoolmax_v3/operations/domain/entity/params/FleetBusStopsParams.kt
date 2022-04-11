package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PostFleetBusStopItems

data class FleetBusStopsParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int
)

data class PostFleetBusStopParams(
    val url: String,
    val sAuthorization: String,
    val postFleetBusStopItems: PostFleetBusStopItems
)

data class SetFleetBusStopStatusParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class CheckDuplicateBusStopParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iRouteID: Int,
    val sStopName: String
)

data class PopulateBusStopsParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iRouteID: Int,
    val iEditMode: Int,
    val iStatusID: Int
)

data class RetrieveBusStopsDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)
