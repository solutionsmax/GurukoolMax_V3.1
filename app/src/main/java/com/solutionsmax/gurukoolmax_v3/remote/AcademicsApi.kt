package com.solutionsmax.gurukoolmax_v3.remote

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.PostCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PostCurriculumInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.RetrieveCurriculumInfoItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PostExamScheduleItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.RetrieveExamScheduleItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.PostExamSetupInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.RetrieveExamSetupDetails
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.PostKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.PostProjectInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PostScholasticRecordingInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.RetrieveScholasticRecordingItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PostSubjectManagementInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.RetrieveSubjectInfoItems
import retrofit2.http.*

interface AcademicsApi {

    // Class
    @GET
    suspend fun populateClassSemester(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iStatusID") iStatusID: Int
    ): MutableList<PopulateClassItems>

    @GET
    suspend fun populateClass(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iStatusID") iStatusID: Int
    ): MutableList<PopulateClassItems>

    // CCE Scholastic
    @POST
    suspend fun postScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postCceScholasticItem: PostCceScholasticItem
    ): Int

    @POST
    suspend fun amendScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postCceScholasticItem: PostCceScholasticItem
    ): Int

    @GET
    suspend fun checkDuplicateScholasticGradingAll(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iMinScore") iMinScore: Int,
        @Query("iMaxScore") iMaxScore: Int,
        @Query("sGrade") sGrade: String,
        @Query("dGradePoint") dGradePoint: Double
    ): Int

    @GET
    suspend fun checkDuplicateScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("sGrade") sGrade: String
    ): Int

    @GET
    suspend fun checkDuplicateScholasticGradingByMaxRange(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iRangeValue") iRangeValue: Int
    ): Int

    @GET
    suspend fun checkDuplicateScholasticGradingByMinRange(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iRangeValue") iRangeValue: Int
    ): Int

    @GET
    suspend fun checkDuplicateScholasticGradingPoint(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("dGradePoint") dGradePoint: Double
    ): Int

    @GET
    suspend fun removeScholasticGradingGrade(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun setStatusScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun retrieveDetailsScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveCceScholasticItem>

    @GET
    suspend fun retrieveListScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveCceScholasticItem>

    @GET
    suspend fun retrieveGradeByListScholasticGrading(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iMarks") iMarks: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveCceScholasticItem>

    // Scholastic Grading
    @POST
    suspend fun postScholasticInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postScholasticRecordingInfoItem: PostScholasticRecordingInfoItem
    ): Int

    @POST
    suspend fun amendScholasticInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postScholasticRecordingInfoItem: PostScholasticRecordingInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateScholasticInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iStudentID") iStudentID: Int,
        @Query("iScholarCategoryID") iScholarCategoryID: Int
    ): Int

    @GET
    suspend fun populateStudentScholasticList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iCategoryID") iCategoryID: Int,
        @Query("iStatusID") iStatusID: Int,
    ): List<PopulateStudentScholasticGradingItem>

    @GET
    suspend fun retrieveScholasticRecordingDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ):List<RetrieveScholasticRecordingItems>

    @GET
    suspend fun retrieveScholasticRecordingList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iStatusID") iStatusID: Int
    ):List<RetrieveScholasticRecordingItems>

    // Academic Project
    @POST
    suspend fun postAcademicProjectInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postProjectInfoItem: PostProjectInfoItem
    ): Int

    @POST
    suspend fun amendAcademicProjectInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postProjectInfoItem: PostProjectInfoItem
    ): Int

    @GET
    suspend fun amendProjectGuideInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("sGuide") sGuide: String,
        @Query("sCourseName") sCourseName: String,
        @Query("sGuideRemarks") sGuideRemarks: String,
        @Query("sRemarksDean") sRemarksDean: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun setAcademicProjectStatus(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun checkDuplicateAcademicProjectInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassStandardID") iClassStandardID: Int,
        @Query("sProjectName") sProjectName: String
    ): Int

    @GET
    suspend fun retrieveAcademicProjectDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveAcademicProjectItem>

    @GET
    suspend fun retrieveAcademicProjectList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveAcademicProjectItem>

    @GET
    suspend fun retrieveAcademicProjectSearchList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardiD") iBoardiD: Int,
        @Query("iClassStandardID") iClassStandardID: Int,
        @Query("sProjectName") sProjectName: String,
        @Query("sTechnology") sTechnology: String,
        @Query("sSuggestedBy") sSuggestedBy: String,
        @Query("sAgency") sAgency: String,
        @Query("sSearchKey") sSearchKey: String,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveAcademicProjectItem>

    // Knowledge Management
    @POST
    suspend fun postKMInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postKMInfo: PostKMInfo
    ): Int

    @POST
    suspend fun amendKMInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postKMInfo: PostKMInfo
    ): Int

    @GET
    suspend fun setStatusKMInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun postFileNameKMInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("sFileName") sFileName: String,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun removeKMInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int,
        @Query("iSubmitterID") iSubmitterID: Int
    ): Int

    @GET
    suspend fun retrieveKMDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveKMInfo>

    @GET
    suspend fun retrieveKMList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassStandardID") iClassStandardID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iFormateTypeID") iFormateTypeID: Int,
        @Query("iContentTypeID") iContentTypeID: Int,
        @Query("iSourceID") iSourceID: Int,
        @Query("iSubmitterID") iSubmitterID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveKMInfo>

    @GET
    suspend fun retrieveKMAdvanceSearch(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassStandardID") iClassStandardID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("sSearchKey") sSearchKey: String,
        @Query("iContentTypeID") iContentTypeID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveKMInfo>

    @GET
    suspend fun retrieveKMSearch(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassStandardID") iClassStandardID: Int,
        @Query("sSearchKey") sSearchKey: String,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveKMInfo>

    /**
     * Subject Management
     */
    @POST
    suspend fun postSubjectManagementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postSubjectManagementInfo: PostSubjectManagementInfo
    ): Int

    @POST
    suspend fun amendSubjectManagementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postSubjectManagementInfo: PostSubjectManagementInfo
    ): Int

    @GET
    suspend fun checkDuplicateSubjectManagementInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iYearID") iYearID: Int
    ): Int

    @GET
    suspend fun populateSubjectList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iStatusID") iStatusID: Int
    ): MutableList<PopulateSubjectList>

    @GET
    suspend fun retrieveSubjectList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveSubjectInfoItems>

    @GET
    suspend fun retrieveSubjectDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveSubjectInfoItems>

    @GET
    suspend fun populateSubjectProgramList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<PopulateSubjectProgramList>

    /**
     * Curriculum
     */
    @POST
    suspend fun postCurriculumInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postCurriculumInfoItem: PostCurriculumInfoItem
    ): Int

    @POST
    suspend fun amendCurriculumInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postCurriculumInfoItem: PostCurriculumInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateCurriculumInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iGradeID") iGradeID: Int,
        @Query("sSectionTitle") sSectionTitle: String,
        @Query("iSubjectID") iSubjectID: Int
    ): Int

    @GET
    suspend fun populateSessionTopicList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iGradeID") iGradeID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<PopulateCurriculumSessionTopicList>

    @GET
    suspend fun populateCurriculumListByClassReference(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iGradeID") iGradeID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<PopulateCurriculumSessionTopicList>

    @GET
    suspend fun retrieveCurriculumDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveCurriculumInfoItems>

    @GET
    suspend fun retrieveCurriculumList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iGradeID") iGradeID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveCurriculumInfoItems>

    // Exam Schedule
    @POST
    suspend fun postExamScheduleInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postExamScheduleItem: PostExamScheduleItem
    ): Int

    @POST
    suspend fun amendExamScheduleInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postExamScheduleItem: PostExamScheduleItem
    ): Int

    @GET
    suspend fun setStatusExamScheduleInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iStatusID") iStatusID: Int,
        @Query("id") id: Int
    ): Int

    @GET
    suspend fun checkDuplicateExamScheduleInfo(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("sScheduleName") sScheduleName: String
    ): Int

    @GET
    suspend fun populateExamScheduleList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<PopulateExamScheduleListItem>

    @GET
    suspend fun retrieveExamScheduleDetails(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveExamScheduleItems>

    @GET
    suspend fun retrieveExamScheduleList(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iStatusID") iStatusID: Int
    ): List<RetrieveExamScheduleItems>

    // Exam Setup
    @POST
    suspend fun postExaminationConfig(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postExamSetupInfoItem: PostExamSetupInfoItem
    ): Int

    @POST
    suspend fun amendExaminationConfig(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Body postExamSetupInfoItem: PostExamSetupInfoItem
    ): Int

    @GET
    suspend fun checkDuplicateExaminationConfig(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iScheduledNameID") iScheduledNameID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("tStartTime") tStartTime: String,
        @Query("tEndTime") tEndTime: String,
        @Query("dExamDate") dExamDate: String,
        @Query("iStatusID") iStatusID: Int
    ): Int

    @GET
    suspend fun retrieveDetailsExaminationConfig(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("id") id: Int
    ): List<RetrieveExamSetupDetails>

    @GET
    suspend fun retrieveListExaminationConfig(
        @Url url: String,
        @Header("Authorization") sAuthorization: String,
        @Query("iGroupID") iGroupID: Int,
        @Query("iSchoolID") iSchoolID: Int,
        @Query("iBoardID") iBoardID: Int,
        @Query("iClassID") iClassID: Int,
        @Query("iYearID") iYearID: Int,
        @Query("iScheduledID") iScheduledID: Int,
        @Query("iSubjectID") iSubjectID: Int,
        @Query("iAssessmentID") iAssessmentID: Int,
        @Query("iStatusID") iStatusID: Int,
        @Query("iStudentID") iStudentID: Int
    ): List<RetrieveExamSetupDetails>
}