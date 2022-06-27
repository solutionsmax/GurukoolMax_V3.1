package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty.PopulateMapSubjectToFacultyItem
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class MapSubjectToFacultyRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {
    suspend fun populateMapSubjectToFacultyList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iFacultyID: Int,
        iSubjectID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateMapSubjectToFacultyItem>> =
        Either.Right(
            academicsApi.populateMapSubjectToFacultyList(
                url = url + MethodConstants.POPULATE_FACULTY_LIST_MAP_SUBJECT_TO_FACULTY,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iFacultyID, iSubjectID, iStatusID
            )
        )
}