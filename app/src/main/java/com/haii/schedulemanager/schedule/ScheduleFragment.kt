package com.haii.schedulemanager.schedule

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.haii.schedulemanager.R
import com.haii.schedulemanager.data.ScheduleItem
import com.haii.schedulemanager.databinding.FragmentScheduleBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import com.haii.schedulemanager.EventObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haii.schedulemanager.ScheduleApplication
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ScheduleViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentScheduleBinding

    private var itemList = listOf<ScheduleItem>()
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var manager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        viewDataBinding = FragmentScheduleBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        scheduleAdapter = ScheduleAdapter()
        scheduleListView.layoutManager = manager
        scheduleListView.adapter = scheduleAdapter
        progressBar.visibility=View.VISIBLE
        init()
        viewModel.getWeek()

    }

    private fun init(){
        viewModel.item.observe(this, Observer{
            itemList = it
            scheduleAdapter.removeItems()
            scheduleAdapter.addItems(itemList)
            scheduleListView.adapter?.notifyDataSetChanged()
            progressBar.visibility = View.GONE

        })

    }

}