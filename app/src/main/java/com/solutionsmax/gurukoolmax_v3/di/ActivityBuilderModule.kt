package com.solutionsmax.gurukoolmax_v3.di

import com.solutionsmax.gurukoolmax_v3.MainActivity
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
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetBusStopsListFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetRoutesListFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetSchedulePickupListFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance.OnBoardAttendanceFragment
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
}