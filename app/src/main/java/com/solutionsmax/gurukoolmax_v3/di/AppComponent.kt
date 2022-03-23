package com.solutionsmax.gurukoolmax_v3.di

import android.app.Application
import com.solutionsmax.gurukoolmax_v3.MyApp
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

    /*@Component.Factory
    interface Factory {
        fun create(utilsModule: UtilsModule): AppComponent
    }*/

    fun inject(myApp: MyApp)
}

/*
@Module
class UtilsModule(private val context: Context) {
    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = context
}*/
