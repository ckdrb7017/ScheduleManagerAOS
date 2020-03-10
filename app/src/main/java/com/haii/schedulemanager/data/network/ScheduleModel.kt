package com.haii.schedulemanager.data.network


import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import retrofit2.Call

interface ScheduleModel {
    fun getWeek(): Call<ScheduleEntity>

    fun getNotice():Call<NoticeItem>
}