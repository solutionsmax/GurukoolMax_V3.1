package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectSearchParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveAcademicProjectSearchListUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : FlowUseCase<List<RetrieveAcademicProjectItem>, AcademicProjectSearchParams>() {
    override suspend fun run(params: AcademicProjectSearchParams): Flow<Either<Failure, List<RetrieveAcademicProjectItem>>> {
        return flowOf(
            academicProjectRepository.retrieveAcademicProjectSearchList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardiD = params.iBoardiD,
                iClassStandardID = params.iClassStandardID,
                sProjectName = params.sProjectName,
                sTechnology = params.sTechnology,
                sSuggestedBy = params.sSuggestedBy,
                sAgency = params.sAgency,
                sSearchKey = params.sSearchKey,
                iStatusID = params.iStatusID
            )
        )
    }
}