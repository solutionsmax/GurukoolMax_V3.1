package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.PostKMInfo

data class KmPostParams(
    val url: String,
    val sAuthorization: String,
    val postKMInfo: PostKMInfo
)

data class KmSetStatusParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class KmPostFileNameParams(
    val url: String,
    val sAuthorization: String,
    val sFileName: String,
    val id: Int
)

data class KmRemoveInfoParams(
    val url: String,
    val sAuthorization: String,
    val id: Int,
    val iSubmitterID: Int
)

data class KmRetrieveDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class KmRetrieveListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val iSubjectID: Int,
    val iFormateTypeID: Int,
    val iContentTypeID: Int,
    val iSourceID: Int,
    val iSubmitterID: Int,
    val iStatusID: Int
)

data class KmRetrieveAdvanceSearchParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val iSubjectID: Int,
    val sSearchKey: String,
    val iContentTypeID: Int,
    val iStatusID: Int
)

data class KmRetrieveSearchParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val sSearchKey: String,
    val iStatusID: Int
)