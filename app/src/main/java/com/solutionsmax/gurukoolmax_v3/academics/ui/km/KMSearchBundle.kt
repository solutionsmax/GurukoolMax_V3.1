package com.solutionsmax.gurukoolmax_v3.academics.ui.km

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KMSearchBundle(
    var iBoardID: Int,
    var iClassID: Int,
    var iSubjectID: Int,
    var iContentTypeID: Int,
    var sSearchKey: String
):Parcelable
