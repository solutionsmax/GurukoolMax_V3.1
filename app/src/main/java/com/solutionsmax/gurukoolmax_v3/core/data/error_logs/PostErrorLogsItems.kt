package com.solutionsmax.gurukoolmax_v3.core.data.error_logs

import com.google.gson.annotations.SerializedName

data class PostErrorLogsItems(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("PLANT_ID")
    val iPlantID: Int,
    @SerializedName("USER_REGISTRATION_ID")
    val iUserRegistrationID: Int,
    @SerializedName("PORTAL_ID")
    val iPortalID: Int,
    @SerializedName("ERROR_MESSAGE")
    val sErrorMessage: String,
    @SerializedName("ERROR_EXCEPTION")
    val sErrorException: String,
    @SerializedName("ERROR_TRACE")
    val sErrorTrace: String,
    @SerializedName("REVIEW_STATUS_ID")
    val iReviewStatusID: Int,
    @SerializedName("ERROR_SOURCE")
    val sErrorSource: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
