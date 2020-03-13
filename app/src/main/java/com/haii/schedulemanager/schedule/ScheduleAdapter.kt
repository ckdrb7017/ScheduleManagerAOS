package com.haii.schedulemanager.schedule

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haii.schedulemanager.common.HEADER_TYPE
import com.haii.schedulemanager.common.ITEM_TYPE
import android.widget.TextView
import com.haii.schedulemanager.R
import com.haii.schedulemanager.data.ScheduleItem


class ScheduleAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var view : View
    var itemList =  arrayListOf<ScheduleItem>()
    val mContext= context


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
        val juaTypeface = Typeface.createFromAsset(mContext?.assets, "font/jua.ttf");

        if(holder is HeaderViewHolder){
            holder.startDay.text = itemList.get(position).startDay
            holder.startDay.setTypeface(juaTypeface);
        }
        else if(holder is ItemViewHolder){
            holder.time.text = itemList.get(position).startTime+"~"+itemList.get(position).endTime
            holder.title.text = itemList.get(position).title
            holder.participant.text = itemList.get(position).participant
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

            itemView.setOnClickListener(){

            }
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