package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.curriculum

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.RetrieveCurriculumInfoItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CurriculumRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostCurriculumInfoUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : UseCase<Int, PostCurriculumInfoParams>() {
    override suspend fun run(params: PostCurriculumInfoParams): Either<Failure, Int> {
        return curriculumRepository.postCurriculumInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postCurriculumInfoItem = params.postCurriculumInfoItem
        )
    }
}

class AmendCurriculumInfoUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : UseCase<Int, PostCurriculumInfoParams>() {
    override suspend fun run(params: PostCurriculumInfoParams): Either<Failure, Int> {
        return curriculumRepository.amendCurriculumInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postCurriculumInfoItem = params.postCurriculumInfoItem
        )
    }
}

class CheckDuplicateCurriculumInfoUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : UseCase<Int, CheckDuplicateCurriculumInfoParams>() {
    override suspend fun run(params: CheckDuplicateCurriculumInfoParams): Either<Failure, Int> {
        return curriculumRepository.checkDuplicateCurriculumInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iGradeID = params.iGradeID,
            sSectionTitle = params.sSectionTitle,
            iSubjectID = params.iSubjectID
        )
    }
}

class PopulateSessionTopicListUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : FlowUseCase<List<PopulateCurriculumSessionTopicList>, PopulateCurriculumSessionTopicListParams>() {
    override suspend fun run(params: PopulateCurriculumSessionTopicListParams): Flow<Either<Failure, List<PopulateCurriculumSessionTopicList>>> {
        return flowOf(
            curriculumRepository.populateSessionTopicList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iGradeID = params.iGradeID,
                iSubjectID = params.iSubjectID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class PopulateCurriculumListByClassReferenceUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : FlowUseCase<List<PopulateCurriculumSessionTopicList>, PopulateCurriculumSessionTopicListParams>() {
    override suspend fun run(params: PopulateCurriculumSessionTopicListParams): Flow<Either<Failure, List<PopulateCurriculumSessionTopicList>>> {
        return flowOf(
            curriculumRepository.populateCurriculumListByClassReference(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iGradeID = params.iGradeID,
                iStatusID = params.iStatusID,
                iSubjectID = params.iSubjectID
            )
        )
    }
}

class RetrieveCurriculumDetailsUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : UseCase<List<RetrieveCurriculumInfoItems>, RetrieveCurriculumDetailsParams>() {
    override suspend fun run(params: RetrieveCurriculumDetailsParams): Either<Failure, List<RetrieveCurriculumInfoItems>> {
        return curriculumRepository.retrieveCurriculumDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}

class RetrieveCurriculumListUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository
) : FlowUseCase<List<RetrieveCurriculumInfoItems>, RetrieveCurriculumListParams>() {
    override suspend fun run(params: RetrieveCurriculumListParams): Flow<Either<Failure, List<RetrieveCurriculumInfoItems>>> {
        return flowOf(
            curriculumRepository.retrieveCurriculumList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iGradeID = params.iGradeID,
                iSubjectID = params.iSubjectID,
                iStatusID = params.iStatusID
            )
        )
    }

}