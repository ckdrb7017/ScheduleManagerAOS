package com.haii.schedulemanager.firebase

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.haii.schedulemanager.R
import com.haii.schedulemanager.ScheduleApplication
import com.haii.schedulemanager.activity.MainActivity
import com.haii.schedulemanager.data.DefaultScheduleRepository
import com.haii.schedulemanager.data.ScheduleRepository
import dagger.android.AndroidInjection
import dagger.android.DaggerBroadcastReceiver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationBroadcastReceiver @Inject constructor() : DaggerBroadcastReceiver() {

    @Inject
    lateinit var repository: DefaultScheduleRepository

    @SuppressLint("MissingSuperCall")
    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)
        intent?.apply {
            if ("REPLY_ACTION" == action){
                val message = replyMessage(this)
                val messageId = getIntExtra("KEY_MESSAGE_ID",0)
                Toast.makeText(context,"$messageId : $message",Toast.LENGTH_LONG).show()
                GlobalScope.launch {
                    repository.getWeek("Haii")
                }
            }

            context?.apply {

                val notificationId = getIntExtra("KEY_NOTIFICATION_ID",0)
                val channelId = getStringExtra("KEY_CHANNEL_ID")

                val builder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.haiilogo)
                    .setContentTitle("Title")
                    .setContentText("message sent!")

                NotificationManagerCompat.from(this).apply {
                    notify(notificationId, builder.build())
                }
            }
        }
    }


    private fun replyMessage(intent: Intent): CharSequence? {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        return remoteInput?.getCharSequence("KEY_TEXT_REPLY")
    }
}