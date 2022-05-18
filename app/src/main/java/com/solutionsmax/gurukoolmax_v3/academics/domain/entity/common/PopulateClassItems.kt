package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common

import com.google.gson.annotations.SerializedName

data class PopulateClassItems(
    @SerializedName("CLASS_STANDARD_ID")
    val iClassStandardID: Int,
    @SerializedName("CLASS_STANDARD")
    val sClassStandard: String
){
    override fun toString(): String {
        return sClassStandard
    }
}
