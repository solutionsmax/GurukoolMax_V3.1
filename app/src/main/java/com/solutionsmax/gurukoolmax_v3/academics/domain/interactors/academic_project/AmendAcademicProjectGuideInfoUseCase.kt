package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.AcademicProjectGuideParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class AmendAcademicProjectGuideInfoUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : UseCase<Int, AcademicProjectGuideParams>() {
    override suspend fun run(params: AcademicProjectGuideParams): Either<Failure, Int> {
        return academicProjectRepository.amendAcademicProjectGuideInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sGuide = params.sGuide,
            sCourseName = params.sCourseName,
            sGuideRemarks = params.sGuideRemarks,
            sRemarksDean = params.sRemarksDean,
            id = params.id
        )
    }
}