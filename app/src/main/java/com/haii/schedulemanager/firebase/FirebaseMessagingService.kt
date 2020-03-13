package com.haii.schedulemanager.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService
import com.haii.schedulemanager.R
import com.haii.schedulemanager.activity.MainActivity
import com.haii.schedulemanager.login.LoginActivity


class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d("TAG", "From: " + remoteMessage.from!!)

        val messageBody = remoteMessage.notification?.body
        val messageTitle = remoteMessage.notification?.title
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val channelId ="1000"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val inboxStyle = NotificationCompat.InboxStyle()

        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.haiilogo)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setOngoing(true)
            .setSound(defaultSoundUri)
            //.setContentIntent(pendingIntent)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.haii))
            .setColor(getResources().getColor(R.color.colorPrimary))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setStyle(inboxStyle)
            .setFullScreenIntent(pendingIntent,true)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName ="ScheduleChannel"

            val channel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor= 0x00FFFF
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0,notificationBuilder.build())



    }

}
