package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.RetrieveExamResultsItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateExamResultParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostExamResultParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamResultDetailsParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamResultListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.ExamResultRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostExamResultUseCase @Inject constructor(
    private val examResultRepository: ExamResultRepository
) : UseCase<Int, PostExamResultParams>() {
    override suspend fun run(params: PostExamResultParams): Either<Failure, Int> {
        return examResultRepository.postExamResult(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamResultItem = params.postExamResultItem
        )
    }
}

class AmendExamResultUseCase @Inject constructor(
    private val examResultRepository: ExamResultRepository
) : UseCase<Int, PostExamResultParams>() {
    override suspend fun run(params: PostExamResultParams): Either<Failure, Int> {
        return examResultRepository.amendExamResult(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamResultItem = params.postExamResultItem
        )
    }
}

class CheckDuplicateExamResultUseCase @Inject constructor(
    private val examResultRepository: ExamResultRepository
) : UseCase<Int, CheckDuplicateExamResultParams>() {
    override suspend fun run(params: CheckDuplicateExamResultParams): Either<Failure, Int> {
        return examResultRepository.checkDuplicateExamResult(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iYearID = params.iYearID,
            iScheduledID = params.iScheduledID,
            iSubjectID = params.iSubjectID,
            iStudentID = params.iStudentID
        )
    }
}

class RetrieveExamResultDetailsUseCase @Inject constructor(
    private val examResultRepository: ExamResultRepository
) : UseCase<List<RetrieveExamResultsItems>, RetrieveExamResultDetailsParams>() {
    override suspend fun run(params: RetrieveExamResultDetailsParams): Either<Failure, List<RetrieveExamResultsItems>> {
        return examResultRepository.retrieveExamResultDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}

class RetrieveExamResultDetailsListUseCase @Inject constructor(
    private val examResultRepository: ExamResultRepository
) : FlowUseCase<List<RetrieveExamResultsItems>, RetrieveExamResultListParams>() {
    override suspend fun run(params: RetrieveExamResultListParams): Flow<Either<Failure, List<RetrieveExamResultsItems>>> {
        return flowOf(
            examResultRepository.retrieveExamResultList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iYearID = params.iYearID,
                iSubjectID = params.iSubjectID,
                iStudentID = params.iStudentID,
                iScheduledID = params.iScheduledID,
                iStatusID = params.iStatusID
            )
        )
    }
}