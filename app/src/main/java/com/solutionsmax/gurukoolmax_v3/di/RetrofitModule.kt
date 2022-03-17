package com.solutionsmax.gurukoolmax_v3.di

import com.solutionsmax.gurukoolmax_v3.core.common.BaseURL
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import com.solutionsmax.gurukoolmax_v3.remote.TokenApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(BaseURL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideTokenApiInterface(retrofit: Retrofit): TokenApi =
        retrofit.create(TokenApi::class.java)

    @Singleton
    @Provides
    fun provideFleetApi(retrofit: Retrofit): FleetApi = retrofit.create(FleetApi::class.java)
}