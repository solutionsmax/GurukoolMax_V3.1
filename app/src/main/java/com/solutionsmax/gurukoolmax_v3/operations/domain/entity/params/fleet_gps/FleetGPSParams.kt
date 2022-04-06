package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSPostItem

data class FleetPostGPSParams(
    val url: String,
    val sAuthorization: String,
    val fleetGPSPostItem: FleetGPSPostItem
)

data class FleetGPSDuplicateParams(
    val url: String,
    val sAuthorization: String,
    val sAddress: String,
    val sLongitude: String,
    val sLatitude: String,
    val sSyncDT: String,
    val iRouteID: Int,
    val iDriverID: Int
)

data class FleetGPSRetrieveDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id:Int
)

data class FleetGPSRetrieveListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iRouteID: Int,
    val iDriverID: Int
)

data class FleetGPSRetrieveListByDateRangeParams(
    val url: String,
    val sAuthorization: String,
    val iRouteID: Int,
    val iDriverID: Int,
    val dFromDate: String,
    val dToDate: String
)