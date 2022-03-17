package com.solutionsmax.gurukoolmax_v3.di

import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.core.ui.view_models.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}