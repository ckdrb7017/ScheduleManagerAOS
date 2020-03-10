package com.haii.schedulemanager.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haii.schedulemanager.R
import com.haii.schedulemanager.data.ScheduleItem
import com.haii.schedulemanager.databinding.FragmentNoticeBinding
import com.haii.schedulemanager.databinding.FragmentScheduleBinding
import com.haii.schedulemanager.schedule.ScheduleAdapter
import com.haii.schedulemanager.schedule.ScheduleViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notice.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import javax.inject.Inject

class NoticeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NoticeViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentNoticeBinding

    private var notice = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notice, container, false)
        viewDataBinding = FragmentNoticeBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        init()
        viewModel.getNotice()
    }

    private fun init(){
        viewModel.notice.observe(this, Observer{
            noticeMain.text = it

        })
    }

}