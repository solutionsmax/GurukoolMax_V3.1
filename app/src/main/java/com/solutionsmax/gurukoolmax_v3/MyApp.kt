package com.solutionsmax.gurukoolmax_v3

import android.app.Activity
import android.app.Application
import com.solutionsmax.gurukoolmax_v3.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    override fun activityInjector() = dispatchingAndroidInjector

}