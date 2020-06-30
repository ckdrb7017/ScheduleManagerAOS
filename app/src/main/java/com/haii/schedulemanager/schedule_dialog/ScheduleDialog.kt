package com.haii.schedulemanager.schedule_dialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.haii.schedulemanager.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject



class ScheduleDialog : DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ScheduleDialogViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_dialog)

        val intent = getIntent()
        val id = intent.getIntExtra("id",0)
        Toast.makeText(this,""+id,Toast.LENGTH_LONG).show()
        viewModel.getScheduleById(id)

    }

}
