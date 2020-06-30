package com.haii.schedulemanager.schedule

import android.view.View
import com.haii.schedulemanager.data.ScheduleItem

interface ListItemClickListener {
    fun onItemClick(item :ScheduleItem)
}