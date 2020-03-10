/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haii.schedulemanager.di

import com.haii.schedulemanager.data.DefaultScheduleRepository
import com.haii.schedulemanager.data.ScheduleRepository
import com.haii.schedulemanager.data.network.ScheduleModel
import com.haii.schedulemanager.data.network.ScheduleModelImpl
import com.haii.schedulemanager.data.network.ScheduleNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME


@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class ScheduleNetworkSource


    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @JvmStatic
    @Singleton
    @Provides
    fun provideOkhttp() : OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }


    @JvmStatic
        @Singleton
        @Provides
        fun provideTaskNetwork() : ScheduleNetwork {
            return Retrofit.Builder()
                .baseUrl("http://192.168.0.35:5000/") //"http://10.0.2.35:5000/"
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkhttp())
                .build()
                .create(ScheduleNetwork::class.java)
    }


    @JvmStatic
    @Singleton
    @Provides
    @ScheduleNetworkSource
    fun provideScheduleModel(): ScheduleModel {
        return ScheduleModelImpl(provideTaskNetwork())

    }



}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultScheduleRepository): ScheduleRepository

}

