package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem

data class OnBoardAttendanceParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val sAdmissionNum: String
)

data class OnBoardCheckAttendanceParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStudentID: Int,
    val sAdmissionNum: String,
    val iRouteID: Int,
    val dDateOfTravel: String
)

data class OnBoardCheckDuplicateAttendanceParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStudentID: Int,
    val iRouteID: Int,
    val dDateOfTravel: String
)

data class OnBoardPostFleetAttendanceParams(
    val url: String,
    val sAuthorization: String,
    val onBoardAttendancePostItem: OnBoardAttendancePostItem
)

data class OnBoardPostFleetAttendanceManuallyParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStudentID: Int,
    val sAdmissionNum: String,
    val iRouteID: Int,
    val dDateOfTravel: String,
    val iAttendanceStatusID: Int
)
