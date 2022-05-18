package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.RetrieveExamSetupDetails
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateExamParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostExamSetupParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamSetupDetailsParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamSetupListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.ExaminationConfigRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostExamSetupInfoUseCase @Inject constructor(
    private val examinationConfigRepository: ExaminationConfigRepository
) : UseCase<Int, PostExamSetupParams>() {
    override suspend fun run(params: PostExamSetupParams): Either<Failure, Int> {
        return examinationConfigRepository.postExaminationConfig(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamSetupInfoItem = params.postExamSetupInfoItem
        )
    }
}

class AmendExamSetupInfoUseCase @Inject constructor(
    private val examinationConfigRepository: ExaminationConfigRepository
) : UseCase<Int, PostExamSetupParams>() {
    override suspend fun run(params: PostExamSetupParams): Either<Failure, Int> {
        return examinationConfigRepository.amendExaminationConfig(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postExamSetupInfoItem = params.postExamSetupInfoItem
        )
    }
}

class CheckDuplicateExamSetupUseCase @Inject constructor(
    private val examinationConfigRepository: ExaminationConfigRepository
) : UseCase<Int, CheckDuplicateExamParams>() {
    override suspend fun run(params: CheckDuplicateExamParams): Either<Failure, Int> {
        return examinationConfigRepository.checkDuplicateExaminationConfig(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iYearID = params.iYearID,
            iScheduledNameID = params.iScheduledNameID,
            iSubjectID = params.iSubjectID,
            tStartTime = params.tStartTime,
            tEndTime = params.tEndTime,
            dExamDate = params.dExamDate,
            iStatusID = params.iStatusID
        )
    }
}

class RetrieveExamSetupDetailUseCase @Inject constructor(
    private val examinationConfigRepository: ExaminationConfigRepository
) : UseCase<List<RetrieveExamSetupDetails>, RetrieveExamSetupDetailsParams>() {
    override suspend fun run(params: RetrieveExamSetupDetailsParams): Either<Failure, List<RetrieveExamSetupDetails>> {
        return examinationConfigRepository.retrieveDetailsExaminationConfig(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}

class RetrieveExamSetupListUseCase @Inject constructor(
    private val examinationConfigRepository: ExaminationConfigRepository
) : FlowUseCase<List<RetrieveExamSetupDetails>, RetrieveExamSetupListParams>() {
    override suspend fun run(params: RetrieveExamSetupListParams): Flow<Either<Failure, List<RetrieveExamSetupDetails>>> {
        return flowOf(
            examinationConfigRepository.retrieveListExaminationConfig(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iYearID = params.iYearID,
                iScheduledID = params.iScheduledID,
                iSubjectID = params.iSubjectID,
                iAssessmentID = params.iAssessmentID,
                iStatusID = params.iStatusID,
                iStudentID = params.iStudentID
            )
        )
    }
}