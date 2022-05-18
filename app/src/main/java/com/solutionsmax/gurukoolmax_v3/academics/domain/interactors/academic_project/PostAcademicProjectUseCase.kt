package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostAcademicProjectParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.AcademicProjectRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class PostAcademicProjectUseCase @Inject constructor(
    private val academicProjectRepository: AcademicProjectRepository
) : UseCase<Int, PostAcademicProjectParams>() {
    override suspend fun run(params: PostAcademicProjectParams): Either<Failure, Int> {
        return academicProjectRepository.postAcademicProjectInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postProjectInfoItem = params.postProjectInfoItem
        )
    }
}