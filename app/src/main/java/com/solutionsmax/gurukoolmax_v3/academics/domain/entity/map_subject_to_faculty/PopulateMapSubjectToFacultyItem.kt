package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty

import com.google.gson.annotations.SerializedName

data class PopulateMapSubjectToFacultyItem(
    @SerializedName("FACULTY_ID")
    val iFacultyID: Int,
    @SerializedName("FACULTY_NAME")
    val sFacultyName: String
){
    override fun toString(): String {
        return sFacultyName
    }
}
