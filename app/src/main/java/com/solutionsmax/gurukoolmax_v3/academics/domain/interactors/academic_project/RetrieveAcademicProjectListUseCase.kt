package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectRetrieveListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveAcademicProjectListUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : FlowUseCase<List<RetrieveAcademicProjectItem>, AcademicProjectRetrieveListParams>() {
    override suspend fun run(params: AcademicProjectRetrieveListParams): Flow<Either<Failure, List<RetrieveAcademicProjectItem>>> {
        return flowOf(
            academicProjectRepository.retrieveAcademicProjectList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iStatusID = params.iStatusID
            )
        )
    }
}