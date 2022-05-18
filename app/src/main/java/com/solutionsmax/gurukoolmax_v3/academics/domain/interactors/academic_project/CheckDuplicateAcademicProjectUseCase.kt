package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectCheckDuplicateParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class CheckDuplicateAcademicProjectUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : UseCase<Int, AcademicProjectCheckDuplicateParams>() {
    override suspend fun run(params: AcademicProjectCheckDuplicateParams): Either<Failure, Int> {
        return academicProjectRepository.checkDuplicateAcademicProjectInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassStandardID = params.iClassStandardID,
            sProjectName = params.sProjectName
        )
    }
}