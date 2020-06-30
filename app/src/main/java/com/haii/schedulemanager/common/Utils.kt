package com.haii.schedulemanager.common

import java.text.SimpleDateFormat
import java.util.*

object Utils{
    val format = SimpleDateFormat("yyyy-MM-dd")
    var cal = Calendar.getInstance()
    val dayList = listOf<String>("일","월","화","수","목","금","토")

    fun getDayOfWeek(day :String) : String{
        cal.time = format.parse(day)

        return dayList[cal.get(Calendar.DAY_OF_WEEK)]
    }

    fun getDate(day : String) :String{
        val month_day = day.split("-")
        return String.format("%s.%s",month_day[1],month_day[2])
    }

    fun getTime(time : String) :String{
        return String.format("%s:%s",time.substring(0,2),time.substring(2,4))
    }


}