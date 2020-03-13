package com.haii.schedulemanager.data.network



import com.haii.schedulemanager.data.NoticeItem
import com.haii.schedulemanager.data.ScheduleEntity
import com.haii.schedulemanager.data.ScheduleItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleNetwork {


    @GET("api/schedule/mobile/{groupName}")
    fun getWeek(
        @Path("groupName") groupName:String
    ): Call<List<ScheduleItem>>

    @GET("api/notice")
    fun getNotice(

    ): Call<List<NoticeItem>>


    @GET("api/schedule/{id}")
    fun getScheduleById(
        @Path("id") id:Int
    ): Call<List<ScheduleItem>>



}