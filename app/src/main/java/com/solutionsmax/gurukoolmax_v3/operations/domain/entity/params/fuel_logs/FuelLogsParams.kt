package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem

data class FuelLogsPostParams(
    val url:String,
    val sAuthorization: String,
    val fuelLogsPostInfoItem: FuelLogsPostInfoItem
)

data class FuelLogsDuplicateParams(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iFleetID: Int,
    val iOdometerID: Int
)

data class FuelLogsRetrieveParams(
    val url:String,
    val sAuthorization: String,
    val id: Int
)

data class FuelLogsRetrieveListParams(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iFleetID: Int,
    val iWorkflowStatusID: Int
)
