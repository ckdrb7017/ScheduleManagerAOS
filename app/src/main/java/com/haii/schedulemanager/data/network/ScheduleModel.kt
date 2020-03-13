package com.haii.schedulemanager.data.network


import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import com.haii.schedulemanager.data.ScheduleItem
import retrofit2.Call
import retrofit2.http.Path

interface ScheduleModel {
    fun getWeek(groupName:String): Call<List<ScheduleItem>>

    fun getNotice():Call<List<NoticeItem>>

    fun getScheduleById(id : Int) : Call<List<ScheduleItem>>
}