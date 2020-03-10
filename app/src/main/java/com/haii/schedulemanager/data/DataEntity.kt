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
                       val viewType : Int=1)



//data class NoticeItem(val notice :String="")


data class NoticeItem(
    val birthday: String,
    val createdDate: String,
    val gender: String,
    val id: Int,
    val image: String,
    val isDeleted: Int,
    val job: String,
    val name: String
)