package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProjectRepoSearchBundle(
    var iBoardID: Int,
    var iClassID: Int,
    var sProjectName: String,
    var sTechnologyInvolved: String,
    var sSearch: String
): Parcelable
