package com.haii.schedulemanager.data.network

import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import com.haii.schedulemanager.data.ScheduleItem
import retrofit2.Call

class ScheduleModelImpl(private val service: ScheduleNetwork): ScheduleModel {


    override fun getWeek(groupName:String): Call<List<ScheduleItem>> {
        return service.getWeek(groupName)
    }

    override fun getNotice(): Call<List<NoticeItem>> {
        return service.getNotice()
    }


    override fun getScheduleById(id : Int): Call<List<ScheduleItem>> {
        return service.getScheduleById(id)
    }


}