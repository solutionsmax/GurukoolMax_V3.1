package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PostBusRouteItem

data class FleetBusRoutesParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int
)

data class PopulateFleetBusRoutesParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStatusID: Int,
)

data class PostBusRoutesParams(
    val url: String,
    val sAuthorization: String,
    val postBusRouteItem: PostBusRouteItem
)

data class BusRouteStatusParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class FetchBusRouteNameParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class DuplicateBusRoutesParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val sRouteName: String
)

data class PopulateBusRoutesParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStatusID: Int
)

data class RetrieveBusRoutesDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveStudentReservationParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iPickupRouteID: Int,
    val iDropRouteID: Int,
    val iStatusID: Int
)