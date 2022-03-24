package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params

data class FleetBusRoutesParams(
    val url:String,
    val sAuthorization: String,
    val iStatusID: Int
)

data class PopulateFleetBusRoutesParams(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStatusID: Int,
)
