package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic.scholastic_recording

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.RetrieveScholasticRecordingItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.ScholasticGradingRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostScholasticRecordingUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : UseCase<Int, PostScholasticRecordingParams>() {
    override suspend fun run(params: PostScholasticRecordingParams): Either<Failure, Int> {
        return scholasticGradingRepository.postScholasticInfo(
            url = params.url,
            sToken = params.sAuthorization,
            postScholasticRecordingInfoItem = params.postScholasticRecordingInfoItem
        )
    }
}

class AmendScholasticRecordingUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : UseCase<Int, PostScholasticRecordingParams>() {
    override suspend fun run(params: PostScholasticRecordingParams): Either<Failure, Int> {
        return scholasticGradingRepository.amendScholasticInfo(
            url = params.url,
            sToken = params.sAuthorization,
            postScholasticRecordingInfoItem = params.postScholasticRecordingInfoItem
        )
    }
}

class CheckDuplicateScholasticRecordingUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : UseCase<Int, CheckDuplicateScholasticRecordingParams>() {
    override suspend fun run(params: CheckDuplicateScholasticRecordingParams): Either<Failure, Int> {
        return scholasticGradingRepository.checkDuplicateScholasticInfo(
            url = params.url,
            sToken = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iSubjectID = params.iSubjectID,
            iStudentID = params.iStudentID,
            iScholarCategoryID = params.iScholarCategoryID
        )
    }
}

class PopulateStudentScholasticListUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : UseCase<List<PopulateStudentScholasticGradingItem>, PopulateStudentScholasticListParams>() {
    override suspend fun run(params: PopulateStudentScholasticListParams): Either<Failure, List<PopulateStudentScholasticGradingItem>> {
        return scholasticGradingRepository.populateStudentScholasticList(
            url = params.url,
            sToken = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iCategoryID = params.iCategoryID,
            iStatusID = params.iStatusID
        )
    }
}

class RetrieveScholasticRecordingDetailsUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : UseCase<List<RetrieveScholasticRecordingItems>, RetrieveScholasticRecordingDetailsParams>() {
    override suspend fun run(params: RetrieveScholasticRecordingDetailsParams): Either<Failure, List<RetrieveScholasticRecordingItems>> {
        return scholasticGradingRepository.retrieveScholasticRecordingDetails(
            url = params.url,
            sToken = params.sAuthorization,
            id = params.id
        )
    }
}

class RetrieveScholasticRecordingListUseCase @Inject constructor(
    private val scholasticGradingRepository: ScholasticGradingRepository
) : FlowUseCase<List<RetrieveScholasticRecordingItems>, RetrieveScholasticRecordingListParams>() {
    override suspend fun run(params: RetrieveScholasticRecordingListParams): Flow<Either<Failure, List<RetrieveScholasticRecordingItems>>> {
        return flowOf(
            scholasticGradingRepository.retrieveScholasticRecordingList(
                url = params.url,
                sToken = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iStatusID = params.iStatusID
            )
        )
    }
}