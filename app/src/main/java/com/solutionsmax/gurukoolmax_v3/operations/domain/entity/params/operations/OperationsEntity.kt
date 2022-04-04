package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.operations

data class ValidateOperationsLogin(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val sUsername: String,
    val sPassword: String
)