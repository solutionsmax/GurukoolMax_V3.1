package com.solutionsmax.gurukoolmax_v3.di

import androidx.lifecycle.ViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.AcademicProjectViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.CceScholasticViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.CceRecordingViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumInfoViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationScheduleViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationSetupViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KmViewModel
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.SettingsViewModel
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.OperationsViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetBusStopsViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetGPSViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetRoutesViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetScheduleViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance.OnBoardAttendanceViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log.FleetFuelLogsViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.FleetMovementViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    abstract fun tokenViewModel(tokenViewModel: TokenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LicenseViewModel::class)
    abstract fun licenseViewModel(licenseViewModel: LicenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetRoutesViewModel::class)
    abstract fun fleetRoutesViewModel(fleetRoutesViewModel: FleetRoutesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisteredFleetViewModel::class)
    abstract fun registeredFleetViewModel(registeredFleetViewModel: RegisteredFleetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetMovementViewModel::class)
    abstract fun fleetMovementViewModel(fleetMovementViewModel: FleetMovementViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetFuelLogsViewModel::class)
    abstract fun fleetFuelLogsViewModel(fleetFuelLogsViewModel: FleetFuelLogsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MastersViewModel::class)
    abstract fun mastersViewModel(mastersViewModel: MastersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TokenLicenseViewModel::class)
    abstract fun tokenLicenseViewModel(tokenViewModel: TokenLicenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardAttendanceViewModel::class)
    abstract fun onBoardAttendanceViewModel(onBoardAttendanceViewModel: OnBoardAttendanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ErrorLogsViewModel::class)
    abstract fun errorLogsViewModel(errorLogsViewModel: ErrorLogsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OperationsViewModel::class)
    abstract fun operationsViewModel(operationsViewModel: OperationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetGPSViewModel::class)
    abstract fun fleetGPSViewModel(fleetGPSViewModel: FleetGPSViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetBusStopsViewModel::class)
    abstract fun fleetBusStopViewModel(fleetBusStopViewModel: FleetBusStopsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FleetScheduleViewModel::class)
    abstract fun fleetScheduleViewModel(fleetBusStopViewModel: FleetScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CceScholasticViewModel::class)
    abstract fun cceSchoolasticViewModel(cceScholasticViewModel: CceScholasticViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AcademicProjectViewModel::class)
    abstract fun academicProjectViewModel(academicProjectViewModel: AcademicProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(KmViewModel::class)
    abstract fun kmViewModel(kmViewModel: KmViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AcademicsViewModel::class)
    abstract fun academicsViewModel(academicsViewModel: AcademicsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun settingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectManagementViewModel::class)
    abstract fun subjectManagementViewModel(subjectManagementViewModel: SubjectManagementViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurriculumInfoViewModel::class)
    abstract fun curriculumInfoViewModel(curriculumInfoViewModel: CurriculumInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExaminationScheduleViewModel::class)
    abstract fun examinationScheduleViewModel(examinationScheduleViewModel: ExaminationScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExaminationSetupViewModel::class)
    abstract fun examinationSetupViewModel(examinationSetupViewModel: ExaminationSetupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CceRecordingViewModel::class)
    abstract fun cceRecordingViewModel(cceRecordingViewModel: CceRecordingViewModel): ViewModel
}