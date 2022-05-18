package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectSetStatusParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class SetAcademicProjectStatusUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : UseCase<Int, AcademicProjectSetStatusParams>() {
    override suspend fun run(params: AcademicProjectSetStatusParams): Either<Failure, Int> {
        return academicProjectRepository.setAcademicProjectStatus(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}