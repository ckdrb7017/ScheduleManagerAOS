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

package com.haii.schedulemanager

import com.haii.schedulemanager.data.DefaultScheduleRepository
import com.haii.schedulemanager.di.DaggerApplicationComponent
import com.haii.schedulemanager.schedule.ScheduleViewModel
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject


/**
 * An [Application] that uses Dagger for Dependency Injection.
 *
 * Also, sets up Timber in the DEBUG BuildConfig. Read Timber's documentation for production setups.
 */
open class ScheduleApplication : DaggerApplication() {

    @Inject
    lateinit var defaultRepository: DefaultScheduleRepository
    lateinit var viewModel: ScheduleViewModel

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        viewModel = ScheduleViewModel(defaultRepository)
    }

    fun getViewmodel() :ScheduleViewModel{
        return viewModel
    }

    companion object {
        private var instance: ScheduleApplication? = null

        val globalApplicationContext: ScheduleApplication
            get() {
                checkNotNull(instance) {}
                return instance!!
            }

    }
}
