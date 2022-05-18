package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management

import com.google.gson.annotations.SerializedName

data class PopulateSubjectProgramList(
    @SerializedName("ID")
    val id:Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName:String
){
    override fun toString(): String {
        return sSubjectName
    }
}
