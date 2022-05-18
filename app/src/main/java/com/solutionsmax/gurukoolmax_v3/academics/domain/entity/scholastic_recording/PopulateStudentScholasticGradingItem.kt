package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording

import com.google.gson.annotations.SerializedName

data class PopulateStudentScholasticGradingItem(
    @SerializedName("STUDENT_ID")
    val iStudentID:Int,
    @SerializedName("STUDENT_NAME")
    val sStudentName:String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID:Int
){
    override fun toString(): String {
        return sStudentName
    }
}