package com.haii.schedulemanager.data.network

import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import retrofit2.Call

class ScheduleModelImpl(private val service: ScheduleNetwork): ScheduleModel {

    override fun getNotice(): Call<NoticeItem> {
        return service.getNotice()
    }

    override fun getWeek(): Call<ScheduleEntity> {
        return service.getWeek()
    }

}