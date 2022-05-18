package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectRetrieveDetailsParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class RetrieveAcademicProjectDetailsUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : UseCase<List<RetrieveAcademicProjectItem>, AcademicProjectRetrieveDetailsParams>() {
    override suspend fun run(params: AcademicProjectRetrieveDetailsParams): Either<Failure, List<RetrieveAcademicProjectItem>> {
        return academicProjectRepository.retrieveAcademicProjectDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}