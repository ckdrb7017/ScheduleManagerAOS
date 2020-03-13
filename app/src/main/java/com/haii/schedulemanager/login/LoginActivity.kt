package com.haii.schedulemanager.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.iid.FirebaseInstanceId
import com.haii.schedulemanager.R
import com.haii.schedulemanager.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.haii.schedulemanager.schedule_dialog.ScheduleDialog

class LoginActivity : AppCompatActivity() {

    lateinit var prefs : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = getSharedPreferences("info", Context.MODE_PRIVATE)
        editor = prefs.edit()

        prefs.getString("id","")
        val groupName = prefs.getString("groupName","")


        button.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)

            //finish()
            if(!groupName.equals("")) {
                FirebaseMessaging.getInstance()
                    .subscribeToTopic(groupName)
                    .addOnCompleteListener { task ->
                        //Toast.makeText(baseContext, "subscribe", Toast.LENGTH_SHORT).show()

                    }
            }
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token
                //Log.d("TAG", ""+token, task.exception)
                //Toast.makeText(baseContext, ""+token, Toast.LENGTH_LONG).show()
            })

        FirebaseMessaging.getInstance()
            .subscribeToTopic("All")
            .addOnCompleteListener { task ->
                //Toast.makeText(baseContext, "subscribe", Toast.LENGTH_SHORT).show()

            }

    }

}
