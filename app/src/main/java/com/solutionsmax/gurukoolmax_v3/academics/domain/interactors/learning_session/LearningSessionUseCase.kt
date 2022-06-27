package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.learning_session

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.RetrieveLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.LearningSessionRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostLearningSessionInfoUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<Int, PostLearningSessionParams>() {
    override suspend fun run(params: PostLearningSessionParams): Either<Failure, Int> {
        return learningSessionRepository.postLearningSessionInfo(
            url = params.url,
            sAuthorization = params.sToken,
            postLearningSessionItem = params.postLearningSessionItem
        )
    }
}

class AmendLearningSessionInfoUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<Int, PostLearningSessionParams>() {
    override suspend fun run(params: PostLearningSessionParams): Either<Failure, Int> {
        return learningSessionRepository.amendLearningSessionInfo(
            url = params.url,
            sAuthorization = params.sToken,
            postLearningSessionItem = params.postLearningSessionItem
        )
    }
}

class AmendLearningSessionProgressUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<Int, AmendLearningSessionProgressParams>() {
    override suspend fun run(params: AmendLearningSessionProgressParams): Either<Failure, Int> {
        return learningSessionRepository.amendLearningSessionProgress(
            url = params.url,
            sAuthorization = params.sToken,
            sFacultyRemarks = params.sFacultyRemarks,
            iProgressStatusID = params.iProgressStatusID,
            dCompletionDate = params.dCompletionDate,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}

class CheckDuplicateLearningSessionUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<Int, CheckDuplicateLearningSessionProgressParams>() {
    override suspend fun run(params: CheckDuplicateLearningSessionProgressParams): Either<Failure, Int> {
        return learningSessionRepository.checkDuplicateLearningSessionInfo(
            url = params.url,
            sAuthorization = params.sToken,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassStandardID = params.iClassStandardID,
            iYearID = params.iYearID,
            iSubjectID = params.iSubjectID,
            iSectionID = params.iSectionID,
            iSessionTopicID = params.iSessionTopicID,
            sTopicDetails = params.sTopicDetails
        )
    }
}

class RetrieveCurriculumTopicListByFacultyUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : FlowUseCase<List<RetrieveLearningSessionItem>, RetrieveCurriculumTopicListByFacultyParams>() {
    override suspend fun run(params: RetrieveCurriculumTopicListByFacultyParams): Flow<Either<Failure, List<RetrieveLearningSessionItem>>> {
        return flowOf(
            learningSessionRepository.retrieveCurriculumTopicListByFaculty(
                url = params.url,
                sAuthorization = params.sToken,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassStandardID = params.iClassStandardID,
                iYearID = params.iYearID,
                iSubjectID = params.iSubjectID,
                iSectionID = params.iSectionID,
                iFacultyID = params.iFacultyID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class RetrieveLearningSessionDetailsUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<List<RetrieveLearningSessionItem>, RetrieveLearningSessionDetailsParams>() {
    override suspend fun run(params: RetrieveLearningSessionDetailsParams): Either<Failure, List<RetrieveLearningSessionItem>> {
        return learningSessionRepository.retrieveLearningSessionDetails(
            url = params.url,
            sAuthorization = params.sToken,
            id = params.id
        )
    }
}

class RetrieveLearningSessionListUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : FlowUseCase<List<RetrieveLearningSessionItem>, RetrieveLearningSessionListParams>() {
    override suspend fun run(params: RetrieveLearningSessionListParams): Flow<Either<Failure, List<RetrieveLearningSessionItem>>> {
        return flowOf(
            learningSessionRepository.retrieveLearningSessionList(
                url = params.url,
                sAuthorization = params.sToken,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassStandardID = params.iClassStandardID,
                iSubjectID = params.iSubjectID,
                iCurriculumID = params.iCurriculumID,
                iFacultyID = params.iFacultyID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class RetrieveLearningSessionListByFacultyUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : FlowUseCase<List<RetrieveLearningSessionItem>, RetrieveLearningSessionListParams>() {
    override suspend fun run(params: RetrieveLearningSessionListParams): Flow<Either<Failure, List<RetrieveLearningSessionItem>>> {
        return flowOf(
            learningSessionRepository.retrieveLearningSessionListByFaculty(
                url = params.url,
                sAuthorization = params.sToken,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassStandardID = params.iClassStandardID,
                iSubjectID = params.iSubjectID,
                iCurriculumID = params.iCurriculumID,
                iFacultyID = params.iFacultyID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class SetLearningSessionStatusUseCase @Inject constructor(
    private val learningSessionRepository: LearningSessionRepository
) : UseCase<Int, SetLearningSessionStatusParams>() {
    override suspend fun run(params: SetLearningSessionStatusParams): Either<Failure, Int> {
        return learningSessionRepository.setLearningSessionStatus(
            url = params.url,
            sAuthorization = params.sToken,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}