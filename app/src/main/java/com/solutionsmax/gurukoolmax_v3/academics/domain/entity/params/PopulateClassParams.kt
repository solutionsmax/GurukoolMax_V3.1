package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

data class PopulateSemesterClassParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iStatusID: Int
)

data class PopulateClassParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iYearId: Int,
    val iStatusID: Int
)