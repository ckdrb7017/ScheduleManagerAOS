package com.haii.schedulemanager.firebase

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.haii.schedulemanager.R
import com.haii.schedulemanager.login.LoginActivity
import androidx.core.app.RemoteInput
import com.haii.schedulemanager.activity.MainActivity
import com.haii.schedulemanager.databinding.CustomToastBinding
import org.w3c.dom.Text

class FirebaseMessagingService : FirebaseMessagingService() {

    val reply_text = "KEY_TEXT_REPLY"
    private lateinit var binding : CustomToastBinding

    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val layoutInflater = LayoutInflater.from(this)
        binding = CustomToastBinding.inflate(layoutInflater)

        Handler(Looper.getMainLooper()).post {

            val layout: View = layoutInflater.inflate(R.layout.custom_toast, binding.customToastContainer)
            var customToast = Toast(this)
            customToast.setGravity(Gravity.TOP, 0, 0)
            customToast.view = layout
            customToast.view.findViewById<TextView>(R.id.title).text = "12312"
            customToast.show()

        }

        //ScheduleApplication.globalApplicationContext.viewModel.getWeek("haii")

        val bitmap = Glide.with(this).asBitmap().load(remoteMessage.data["imageURL"]).submit().get()

        val messageBody = remoteMessage.data["body"]
        val messageTitle = remoteMessage.data["title"]
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val channelId = "$packageName.channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val style = NotificationCompat.BigTextStyle()
        style.bigText(messageBody)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

     /*   val notification = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.haiilogo)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setLargeIcon(bitmap)
            .setColor(resources.getColor(R.color.colorPrimary))
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(style)
            .setOnlyAlertOnce(true)
            .setLights(Color.BLUE,1,1)
            .setContentIntent(pendingIntent)
            .build()



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName ="ScheduleChannel"

            val channel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableLights(true)
            channel.lightColor= 0x00FFFF
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1000,notification)*/




        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            var replyLabel = "답장하기"
            var remoteInput = RemoteInput.Builder(reply_text).run {
                setLabel(replyLabel)
                build()
            }

            val replyIntent = Intent(this, NotificationBroadcastReceiver::class.java)
            replyIntent.action = "REPLY_ACTION"
            replyIntent.putExtra("KEY_NOTIFICATION_ID", 1000)
            replyIntent.putExtra("KEY_CHANNEL_ID", channelId)
            replyIntent.putExtra("KEY_MESSAGE_ID", 2)
            val replyPendingIntent =
                PendingIntent.getBroadcast(this, 1000, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            var action = NotificationCompat.Action.Builder(R.drawable.haiilogo,
                    "답장", replyPendingIntent)
                    .addRemoteInput(remoteInput)
                    .setAllowGeneratedReplies(true)
                    .build()

            val newMessageNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.haiilogo)
                .setLargeIcon(bitmap)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setOnlyAlertOnce(true)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setStyle(style)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(action)
                .build()

            with(NotificationManagerCompat.from(this)) {
                val channel = NotificationChannel(channelId,"ScheduleChannel", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
                notificationManager.notify(1000, newMessageNotification)
            }
        }






    }



}
