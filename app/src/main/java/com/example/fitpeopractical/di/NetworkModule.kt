package com.example.fitpeopractical.di


import android.content.Context
import com.example.fitpeopractical.network.ApiRestService
import com.example.fitpeopractical.network.interceptor.HeaderInterceptor
import com.example.fitpeopractical.network.interceptor.NetworkInterceptor
import com.example.fitpeopractical.util.DeviceUtil
import com.example.fitpeopractical.util.PreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jeetesh Surana.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getNetworkInterceptor(@ApplicationContext appContext: Context) =
        NetworkInterceptor(appContext)

    @Provides
    @Singleton
    fun getHeaderInterceptor() =
        HeaderInterceptor()

    @Provides
    @Singleton
    fun getApiRestService(
        @ApplicationContext appContext: Context,
        networkInterceptor: NetworkInterceptor,
        headerInterceptor: HeaderInterceptor
    ) = ApiRestService(appContext, networkInterceptor, headerInterceptor)
}