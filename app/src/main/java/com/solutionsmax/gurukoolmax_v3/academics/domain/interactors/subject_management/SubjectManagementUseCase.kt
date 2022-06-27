package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.subject_management

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.RetrieveSubjectInfoItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.SubjectManagementRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PostSubjectManagementInfoUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : UseCase<Int, PostSubjectInfoParams>() {
    override suspend fun run(params: PostSubjectInfoParams): Either<Failure, Int> {
        return subjectManagementRepository.postSubjectManagementInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postSubjectManagementInfo = params.postSubjectManagementInfo
        )
    }
}

class AmendSubjectManagementInfoUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : UseCase<Int, PostSubjectInfoParams>() {
    override suspend fun run(params: PostSubjectInfoParams): Either<Failure, Int> {
        return subjectManagementRepository.amendSubjectManagementInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postSubjectManagementInfo = params.postSubjectManagementInfo
        )
    }
}

class CheckDuplicateSubjectManagementInfoUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : UseCase<Int, CheckDuplicateSubjectInfoParams>() {
    override suspend fun run(params: CheckDuplicateSubjectInfoParams): Either<Failure, Int> {
        return subjectManagementRepository.checkDuplicateSubjectManagementInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iSubjectID = params.iSubjectID,
            iYearID = params.iYearID
        )
    }
}

class PopulateSubjectListUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : FlowUseCase<MutableList<PopulateSubjectList>, PopulateSubjectListParams>() {
    override suspend fun run(params: PopulateSubjectListParams): Flow<Either<Failure, MutableList<PopulateSubjectList>>> {
        return flowOf(
            subjectManagementRepository.populateSubjectList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iYearID = params.iYearID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class RetrieveSubjectListUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : FlowUseCase<List<RetrieveSubjectInfoItems>, RetrieveSubjectListParams>() {
    override suspend fun run(params: RetrieveSubjectListParams): Flow<Either<Failure, List<RetrieveSubjectInfoItems>>> {
        return flowOf(
            subjectManagementRepository.retrieveSubjectList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iYearID = params.iYearID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class RetrieveSubjectDetailsUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : UseCase<List<RetrieveSubjectInfoItems>, RetrieveSubjectDetailsParams>() {
    override suspend fun run(params: RetrieveSubjectDetailsParams): Either<Failure, List<RetrieveSubjectInfoItems>> {
        return subjectManagementRepository.retrieveSubjectDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}

class PopulateSubjectProgramsListUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : FlowUseCase<List<PopulateSubjectProgramList>, PopulateSubjectProgramsListParams>() {
    override suspend fun run(params: PopulateSubjectProgramsListParams): Flow<Either<Failure, List<PopulateSubjectProgramList>>> {
        return flowOf(
            subjectManagementRepository.populateSubjectProgramList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class SetSubjectProgramStatusUseCase @Inject constructor(
    private val subjectManagementRepository: SubjectManagementRepository
) : UseCase<Int, SetStatusSubjectProgramParams>() {
    override suspend fun run(params: SetStatusSubjectProgramParams): Either<Failure, Int> {
        return subjectManagementRepository.setStatusSubjectManagement(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}