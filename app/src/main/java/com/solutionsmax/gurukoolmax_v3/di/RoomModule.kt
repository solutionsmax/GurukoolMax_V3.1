package com.solutionsmax.gurukoolmax_v3.di

import android.app.Application
import androidx.room.Room
import com.solutionsmax.gurukoolmax_v3.room.AppDatabase
import com.solutionsmax.gurukoolmax_v3.room.dao.LicenseDAO
import com.solutionsmax.gurukoolmax_v3.room.dao.TokenDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigrationFrom()
            .build()

    @Singleton
    @Provides
    fun provideTokenDao(db: AppDatabase): TokenDAO =
        db.tokenDao()

    @Singleton
    @Provides
    fun provideLicenseDao(db: AppDatabase): LicenseDAO =
        db.licenseDao()

}