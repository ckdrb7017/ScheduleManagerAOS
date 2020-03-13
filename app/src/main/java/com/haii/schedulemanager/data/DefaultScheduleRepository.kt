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
package com.haii.schedulemanager.data

import android.util.Log
import com.haii.schedulemanager.data.network.ScheduleModel
import com.haii.schedulemanager.di.ApplicationModule
import com.haii.schedulemanager.di.ApplicationModule.ScheduleNetworkSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject


class DefaultScheduleRepository @Inject constructor(

    @ScheduleNetworkSource private val scheduleModel: ScheduleModel,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ScheduleRepository {


    lateinit var weekSchedules : List<ScheduleItem>
    lateinit var oneSchedule : ScheduleItem


    override suspend fun getWeek(groupName:String): List<ScheduleItem> {
        withContext(ioDispatcher){
            val request = scheduleModel.getWeek(groupName)
            val response = request.await()

            if(response !=null){
                Log.d("TAG","getWeek : "+response.toString())
                weekSchedules = response
            }else{
                Log.d("TAG","getWeek : Null"+response.toString())
            }


        }
        return weekSchedules

    }

    override suspend fun getNotice(): NoticeItem {

        Log.d("TAG","getNotice")
        var notice = listOf<NoticeItem>()
        withContext(ioDispatcher){
            val request = scheduleModel.getNotice()
            val response = request.await()
            notice = response
            Log.d("TAG",""+notice)
        }
        return notice.get(0)
    }


    override suspend fun getScheduleById(id : Int): ScheduleItem {
        withContext(ioDispatcher){
            val request = scheduleModel.getScheduleById(id)
            val response = request.await()

            if(response !=null){
                Log.d("TAG","oneSchedule : "+response.toString())
                oneSchedule = response.get(0)
            }


        }
        return oneSchedule
    }


}
