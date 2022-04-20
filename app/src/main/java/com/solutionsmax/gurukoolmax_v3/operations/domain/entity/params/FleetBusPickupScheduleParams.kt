package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_schedule.PostBusScheduleItem

data class FleetBusPickupScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iRouteID: Int,
    val iStatusID: Int
)

data class PostFleetBusScheduleParams(
    val url: String,
    val sAuthorization: String,
    val postBusScheduleItem: PostBusScheduleItem
)

data class SetStatusFleetBusScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class CheckDuplicateFleetBusScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iRouteID: Int,
    val iStopID: Int
)

data class RetrieveDetailsFleetBusScheduleParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveStudentBusScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iRouteID: Int,
    val iSortID: Int,
    val iStatusID: Int
)