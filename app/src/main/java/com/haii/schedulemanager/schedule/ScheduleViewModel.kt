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
package com.haii.schedulemanager.schedule

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.haii.schedulemanager.data.ScheduleItem
import com.haii.schedulemanager.data.ScheduleRepository
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {


    private val _item = MutableLiveData<List<ScheduleItem>>()
    val item: LiveData<List<ScheduleItem>> = _item

    private val _oneSchedule = MutableLiveData<ScheduleItem>()
    val oneSchedule: LiveData<ScheduleItem> = _oneSchedule
    lateinit var mOneSchedule : ScheduleItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getWeek(groupName : String){
        //_isLoading.value = false
        viewModelScope.launch {
            val itemList = scheduleRepository.getWeek(groupName)
            if(itemList.isEmpty()){
                Log.d("TAG","Null")
                _item.value=null
            }else{
                _item.value = itemList
            }
            //_isLoading.value = true
        }
    }

 /*   fun getScheduleById(id : Int){

        viewModelScope.launch {

            mOneSchedule = scheduleRepository.getScheduleById(id)
            _oneSchedule.value = mOneSchedule

        }
    }*/

    fun onRefresh(){
        Log.d("TAG","onRefresh")
        getWeek("Haii")
    }
}
