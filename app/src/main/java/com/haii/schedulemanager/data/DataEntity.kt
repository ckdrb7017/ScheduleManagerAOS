package com.haii.schedulemanager.data


data class ScheduleEntity(val body: ScheduleItem,
                          val statusCode: Int = 0)


data class ScheduleItem(val id: Int = 0,
                       val groupName: String = "",
                       val startDay: String = "",
                       val startTime: String = "",
                       val endTime: String = "",
                       val place: String = "",
                       val title: String = "",
                       val participant :String="",
                       val isExpanded :Boolean=false,
                       val viewType : Int=0)



data class NoticeItem(val noticeText :String="")
