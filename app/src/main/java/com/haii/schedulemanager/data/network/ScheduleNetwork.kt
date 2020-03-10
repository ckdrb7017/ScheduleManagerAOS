package com.haii.schedulemanager.data.network



import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleNetwork {


    @GET("api/customers11")
    fun getWeek(

    ): Call<ScheduleEntity>

    @GET("api/customers")
    fun getNotice(

    ): Call<NoticeItem>




}