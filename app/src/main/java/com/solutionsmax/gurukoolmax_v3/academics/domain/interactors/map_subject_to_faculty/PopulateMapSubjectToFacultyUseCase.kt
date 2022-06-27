package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.map_subject_to_faculty

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty.PopulateMapSubjectToFacultyItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateMapSubjectToFacultyParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.MapSubjectToFacultyRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class PopulateMapSubjectToFacultyUseCase @Inject constructor(
    private val mapSubjectToFacultyRepository: MapSubjectToFacultyRepository
) : UseCase<List<PopulateMapSubjectToFacultyItem>, PopulateMapSubjectToFacultyParams>() {
    override suspend fun run(params: PopulateMapSubjectToFacultyParams): Either<Failure, List<PopulateMapSubjectToFacultyItem>> {
        return mapSubjectToFacultyRepository.populateMapSubjectToFacultyList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iFacultyID = params.iFacultyID,
            iSubjectID = params.iSubjectID,
            iStatusID = params.iStatusID
        )
    }
}