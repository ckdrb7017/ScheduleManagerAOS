package com.haii.schedulemanager.schedule

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.recyclerview.widget.RecyclerView
import com.haii.schedulemanager.common.HEADER_TYPE
import com.haii.schedulemanager.common.ITEM_TYPE
import android.widget.TextView
import com.haii.schedulemanager.R
import com.haii.schedulemanager.common.Utils
import com.haii.schedulemanager.common.Utils.cal
import com.haii.schedulemanager.common.Utils.format
import com.haii.schedulemanager.common.Utils.getDate
import com.haii.schedulemanager.common.Utils.getDayOfWeek
import com.haii.schedulemanager.common.Utils.getTime
import com.haii.schedulemanager.data.ScheduleItem


class ScheduleAdapter(context: Context?,listener: ListItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var view : View
    var itemList =  arrayListOf<ScheduleItem>()
    val mListener = listener
    val juaTypeface = Typeface.createFromAsset(context?.assets, "jua.ttf")
    val mapoTypeface = Typeface.createFromAsset(context?.assets, "mapopeace.ttf")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(viewType == HEADER_TYPE){
            view = inflater.inflate(R.layout.schedule_header,parent,false)
            return HeaderViewHolder(view)
        }
        else if(viewType == ITEM_TYPE){
            view = inflater.inflate(R.layout.schedule_item,parent,false)
            return ItemViewHolder(view)
        }

        return HeaderViewHolder(view)

    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is HeaderViewHolder -> {

                holder.startDay.text = getDate(itemList.get(position).startDay) +
                        "(" + getDayOfWeek(itemList.get(position).startDay) + ")"
                holder.startDay.typeface = juaTypeface

            }

            is ItemViewHolder-> {
    /*            holder.time.text =
                    getTime(itemList.get(position).startTime) + " ~ " + getTime(itemList.get(position).endTime)
                holder.title.text = itemList.get(position).title
                holder.place.text = itemList.get(position).place
                holder.participant.text = itemList.get(position).participant

                holder.time.typeface = mapoTypeface
                holder.title.typeface = mapoTypeface
                holder.place.typeface = mapoTypeface
                holder.participant.typeface = mapoTypeface

                holder.itemView.setOnClickListener {
                    mListener.onItemClick(itemList.get(position))
                }*/
            }
        }
    }


    class HeaderViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var startDay: TextView

        init {
            startDay = itemView.findViewById(R.id.startDay)
        }
    }

    class ItemViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var time: TextView
        internal var title: TextView
        internal var participant: TextView
        internal var place: TextView


        init {
            time = itemView.findViewById(R.id.time)
            title = itemView.findViewById(R.id.title)
            participant = itemView.findViewById(R.id.participant)
            place = itemView.findViewById(R.id.place)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return itemList.get(position).viewType
    }

    override fun getItemId(position: Int): Long {
        return itemList.get(position).id.toLong()
    }

    fun addItems(item : List<ScheduleItem>){
        itemList.addAll(item)
    }

    fun removeItems(){
        itemList.clear()
    }


}