package com.solutionsmax.gurukoolmax_v3.di

import android.app.Application
import com.solutionsmax.gurukoolmax_v3.MyApp
import com.solutionsmax.gurukoolmax_v3.core.utils.NetworkHandler
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RetrofitModule::class,
        RoomModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApp: MyApp)
}