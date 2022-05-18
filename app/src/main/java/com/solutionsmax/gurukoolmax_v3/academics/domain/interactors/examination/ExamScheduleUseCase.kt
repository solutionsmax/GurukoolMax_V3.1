package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.RetrieveExamScheduleItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.ExamScheduleRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostExamScheduleInfoUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : UseCase<Int, PostExamScheduleParams>() {
    override suspend fun run(params: PostExamScheduleParams): Either<Failure, Int> {
        return examScheduleRepository.postExamScheduleInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamScheduleItem = params.postExamScheduleItem
        )
    }
}

class AmendExamScheduleInfoUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : UseCase<Int, PostExamScheduleParams>() {
    override suspend fun run(params: PostExamScheduleParams): Either<Failure, Int> {
        return examScheduleRepository.amendExamScheduleInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamScheduleItem = params.postExamScheduleItem
        )
    }
}

class SetStatusExamScheduleUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : UseCase<Int, SetStatusExamScheduleParams>() {
    override suspend fun run(params: SetStatusExamScheduleParams): Either<Failure, Int> {
        return examScheduleRepository.setStatusExamScheduleInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}

class CheckDuplicateExamScheduleUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : UseCase<Int, CheckDuplicateExamScheduleParams>() {
    override suspend fun run(params: CheckDuplicateExamScheduleParams): Either<Failure, Int> {
        return examScheduleRepository.checkDuplicateExamScheduleInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iYearID = params.iYearID,
            sScheduleName = params.sScheduleName
        )
    }
}

class PopulateExamScheduleListUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : FlowUseCase<List<PopulateExamScheduleListItem>, PopulateExamScheduleParams>() {
    override suspend fun run(params: PopulateExamScheduleParams): Flow<Either<Failure, List<PopulateExamScheduleListItem>>> {
        return flowOf(
            examScheduleRepository.populateExamScheduleList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iYearID = params.iYearID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class RetrieveExamScheduleDetailsUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : UseCase<List<RetrieveExamScheduleItems>, RetrieveDetailsExamScheduleParams>() {
    override suspend fun run(params: RetrieveDetailsExamScheduleParams): Either<Failure, List<RetrieveExamScheduleItems>> {
        return examScheduleRepository.retrieveExamScheduleDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}

class RetrieveExamScheduleListUseCase @Inject constructor(
    private val examScheduleRepository: ExamScheduleRepository
) : FlowUseCase<List<RetrieveExamScheduleItems>, RetrieveListExamScheduleParams>() {
    override suspend fun run(params: RetrieveListExamScheduleParams): Flow<Either<Failure, List<RetrieveExamScheduleItems>>> {
        return flowOf(
            examScheduleRepository.retrieveExamScheduleList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iStatusID = params.iStatusID
            )
        )
    }
}