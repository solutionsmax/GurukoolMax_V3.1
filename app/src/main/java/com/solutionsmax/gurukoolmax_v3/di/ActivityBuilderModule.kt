package com.solutionsmax.gurukoolmax_v3.di

import com.solutionsmax.gurukoolmax_v3.MainActivity
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsCommonBottomDialogSheetFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.AdministratorLoginFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.AdministratorMenuFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.AdministratorSubMenuFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.ProjectRepositorySearchResultFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.ProjectSubmissionInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.ProjectsRepositoryListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.ProjectsRepositorySearchFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.activities.CceScholasticActivityInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.activities.CceScholasticActivityListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system.ScholasticGradeSystemInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system.ScholasticGradeSystemListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.performance.AcademicsPerformanceSearchFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.CceScholasticInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.CceScholasticListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.schedule_online_lesson_plan_sessions.OnlineSessionLessonsPlanInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.schedule_online_lesson_plan_sessions.OnlineSessionLessonsPlanListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.SubjectManagementInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.SubjectManagementListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.*
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMPostRepositoryInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMRepositoryListFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMSearchRepositoryFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KmSearchResultListFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.MainMenuFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.RegisterSchoolCodeFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.SplashFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderFourFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderOneFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderThreeFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderTwoFragment
import com.solutionsmax.gurukoolmax_v3.management.ManagementLoginFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.OperationsLoginFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.OperationsMenuFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.OperationsSubMenuFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.InitiateGpsTrackerFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.ViewGpsMapFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.*
import com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance.OnBoardAttendanceFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance.OnBoardAttendanceSelectRouteFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance.OnBoardManualAttendanceFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log.FleetFuelLogInfoFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log.FleetFuelLogListFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.RegisteredFleetMovementInfoFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.RegisteredFleetMovementListFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetInfoFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideSliderOneFragment(): SliderOneFragment

    @ContributesAndroidInjector
    abstract fun provideSliderTwoFragment(): SliderTwoFragment

    @ContributesAndroidInjector
    abstract fun provideSliderThreeFragment(): SliderThreeFragment

    @ContributesAndroidInjector
    abstract fun provideSliderFourFragment(): SliderFourFragment

    @ContributesAndroidInjector
    abstract fun provideSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun provideMainMenuLogin(): MainMenuFragment

    @ContributesAndroidInjector
    abstract fun provideManagementLogin(): ManagementLoginFragment

    @ContributesAndroidInjector
    abstract fun provideRegisterSchoolCodeFragment(): RegisterSchoolCodeFragment

    @ContributesAndroidInjector
    abstract fun provideOperationsLoginFragment(): OperationsLoginFragment

    @ContributesAndroidInjector
    abstract fun provideOperationsMenuFragment(): OperationsMenuFragment

    @ContributesAndroidInjector
    abstract fun provideOperationsSubMenuFragment(): OperationsSubMenuFragment

    @ContributesAndroidInjector
    abstract fun provideFleetRoutesListFragment(): FleetRoutesListFragment

    @ContributesAndroidInjector
    abstract fun contributeFleetBusStopFragment(): FleetBusStopsListFragment

    @ContributesAndroidInjector
    abstract fun contributeFleetPickupSchedule(): FleetSchedulePickupListFragment

    @ContributesAndroidInjector
    abstract fun provideOnBoardAttendanceFragment(): OnBoardAttendanceFragment

    @ContributesAndroidInjector
    abstract fun provideRegisteredFleetListFragment(): RegisteredFleetListFragment

    @ContributesAndroidInjector
    abstract fun provideRegisteredFleetInfoFragment(): RegisteredFleetInfoFragment

    @ContributesAndroidInjector
    abstract fun provideRegisteredFleetMovementListFragment(): RegisteredFleetMovementListFragment

    @ContributesAndroidInjector
    abstract fun provideRegisteredFleetMovementInfoFragment(): RegisteredFleetMovementInfoFragment

    @ContributesAndroidInjector
    abstract fun provideFleetFuelLogListFragment(): FleetFuelLogListFragment

    @ContributesAndroidInjector
    abstract fun provideFleetFuelLogInfoFragment(): FleetFuelLogInfoFragment

    @ContributesAndroidInjector
    abstract fun provideOnBoardAttendanceSelectRouteFragment(): OnBoardAttendanceSelectRouteFragment

    @ContributesAndroidInjector
    abstract fun provideOnBoardManualAttendanceFragment(): OnBoardManualAttendanceFragment

    @ContributesAndroidInjector
    abstract fun provideFleetRoutesInfoInoFragment(): FleetRoutesInfoFragment

    @ContributesAndroidInjector
    abstract fun provideFleetBusStopInfoFragment(): FleetBusStopInfoFragment

    @ContributesAndroidInjector
    abstract fun provideFleetBusScheduleInfoFragment(): FleetScheduleInfoFragment

    @ContributesAndroidInjector
    abstract fun provideInitiateGpsTrackerFragment(): InitiateGpsTrackerFragment

    @ContributesAndroidInjector
    abstract fun provideViewGpsMapFragment(): ViewGpsMapFragment

    @ContributesAndroidInjector
    abstract fun provideAcademicsCommonBottomDialogSheetFragment(): AcademicsCommonBottomDialogSheetFragment

    @ContributesAndroidInjector
    abstract fun provideScholasticGradeSystemList(): ScholasticGradeSystemListFragment

    @ContributesAndroidInjector
    abstract fun provideScholasticGradeSystemInfo(): ScholasticGradeSystemInfoFragment

    @ContributesAndroidInjector
    abstract fun provideCceScholasticList(): CceScholasticListFragment

    @ContributesAndroidInjector
    abstract fun provideCceScholasticInfo(): CceScholasticInfoFragment

    @ContributesAndroidInjector
    abstract fun provideCceScholasticActivityList(): CceScholasticActivityListFragment

    @ContributesAndroidInjector
    abstract fun provideCceScholasticActivityInfo(): CceScholasticActivityInfoFragment

    @ContributesAndroidInjector
    abstract fun provideAcademicsPerformanceSearch(): AcademicsPerformanceSearchFragment

    @ContributesAndroidInjector
    abstract fun provideSubjectManagementList(): SubjectManagementListFragment

    @ContributesAndroidInjector
    abstract fun provideSubjectManagementInfo(): SubjectManagementInfoFragment

    @ContributesAndroidInjector
    abstract fun provideCurriculumSetupList(): CurriculumSetupListFragment

    @ContributesAndroidInjector
    abstract fun provideCurriculumSetupInfo(): CurriculumSetupInfoFragment

    @ContributesAndroidInjector
    abstract fun provideAdministratorLogin(): AdministratorLoginFragment

    @ContributesAndroidInjector
    abstract fun provideAdministratorMenuFragment(): AdministratorMenuFragment

    @ContributesAndroidInjector
    abstract fun provideAdministrationSubMenuFragment(): AdministratorSubMenuFragment

    @ContributesAndroidInjector
    abstract fun provideSetupLearningSessionList(): SetupLearningSessionListFragment

    @ContributesAndroidInjector
    abstract fun provideSetupLearningSessionInfo(): SetupLearningSessionInfoFragment

    @ContributesAndroidInjector
    abstract fun provideOnlineLearningSessionList(): OnlineSessionLessonsPlanListFragment

    @ContributesAndroidInjector
    abstract fun provideOnlineLearningSessionInfo(): OnlineSessionLessonsPlanInfoFragment

    @ContributesAndroidInjector
    abstract fun provideProjectRepositoryList(): ProjectsRepositoryListFragment

    @ContributesAndroidInjector
    abstract fun provideProjectRepositoryInfo(): ProjectSubmissionInfoFragment

    @ContributesAndroidInjector
    abstract fun provideProjectRepositorySearch(): ProjectsRepositorySearchFragment

    @ContributesAndroidInjector
    abstract fun provideKmRepositoryList(): KMRepositoryListFragment

    @ContributesAndroidInjector
    abstract fun provideKmRepositoryInfo(): KMPostRepositoryInfoFragment

    @ContributesAndroidInjector
    abstract fun provideKmSearch(): KMSearchRepositoryFragment

    @ContributesAndroidInjector
    abstract fun provideExamScheduleList(): ExamScheduleListFragment

    @ContributesAndroidInjector
    abstract fun provideExamScheduleInfo(): ExamScheduleInfoFragment

    @ContributesAndroidInjector
    abstract fun provideExamSetupList(): ExaminationSetupListFragment

    @ContributesAndroidInjector
    abstract fun provideExamSetupInfo(): ExaminationSetupInfoFragment

    @ContributesAndroidInjector
    abstract fun provideExaminationResultList(): ExaminationResultsListFragment

    @ContributesAndroidInjector
    abstract fun provideExaminationResultInfo(): ExaminationResultsInfoFragment

    @ContributesAndroidInjector
    abstract fun provideProjectRepositorySearchResult(): ProjectRepositorySearchResultFragment

    @ContributesAndroidInjector
    abstract fun provideKMSearchResultFragment(): KmSearchResultListFragment
}