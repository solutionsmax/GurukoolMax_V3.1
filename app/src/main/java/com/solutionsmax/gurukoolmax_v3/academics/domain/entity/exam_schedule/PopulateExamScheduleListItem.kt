package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule

import com.google.gson.annotations.SerializedName

data class PopulateExamScheduleListItem(
    @SerializedName("ID")
    val id:Int,
    @SerializedName("SCHEDULE_NAME")
    val sScheduleName:String,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID:Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID:Int
){
    override fun toString(): String {
        return sScheduleName
    }
}
