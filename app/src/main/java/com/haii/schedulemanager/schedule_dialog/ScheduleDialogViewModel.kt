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
package com.haii.schedulemanager.schedule_dialog

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haii.schedulemanager.data.NoticeItem

import com.haii.schedulemanager.data.ScheduleItem
import com.haii.schedulemanager.data.ScheduleRepository
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

/**
 * ViewModel for the task list screen.
 */
class ScheduleDialogViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {


    private val _oneSchedule = MutableLiveData<ScheduleItem>()
    val oneSchedule: LiveData<ScheduleItem> = _oneSchedule
    lateinit var mOneSchedule : ScheduleItem
    /*
    private val _items = MutableLiveData<List<ScheduleItem>>().apply { value = emptyList() }
    val items: LiveData<List<ScheduleItem>> = _items

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }*/


    fun getScheduleById(id : Int){

        viewModelScope.launch {
            mOneSchedule = scheduleRepository.getScheduleById(id)
            _oneSchedule.value = mOneSchedule

            Log.d("TAG",""+_oneSchedule.value)
        }
    }


}
