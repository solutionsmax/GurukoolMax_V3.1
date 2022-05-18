package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum

import com.google.gson.annotations.SerializedName

data class PopulateCurriculumSessionTopicList(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("SECTION_TITLE")
    val sSectionTitle: String
) {
    override fun toString(): String {
        return sSectionTitle
    }
}