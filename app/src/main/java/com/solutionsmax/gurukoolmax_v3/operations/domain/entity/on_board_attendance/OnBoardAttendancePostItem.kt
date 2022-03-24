package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance

import com.google.gson.annotations.SerializedName

data class OnBoardAttendancePostItem(
    @SerializedName("ID")
    var id: Int?=0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("STUDENT_ID")
    val iStudentID: Int,
    @SerializedName("BARCODE_SERIAL")
    val sBarcodeSerial: String,
    @SerializedName("ROUTE_ID")
    val iRouteID: Int,
    @SerializedName("DATE_OF_TRAVEL")
    val sDateOfTravel: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusId: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
