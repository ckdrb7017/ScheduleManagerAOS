package com.haii.schedulemanager.activity

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.RemoteInput
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.haii.schedulemanager.R
import com.haii.schedulemanager.databinding.ActivityMainBinding
import com.haii.schedulemanager.databinding.CustomToastBinding

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var binding : ActivityMainBinding

    private lateinit var ioDispatcher : CoroutineDispatcher




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationDrawer()
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this,
            R.id.nav_fragment
        )
        appBarConfiguration = AppBarConfiguration
            .Builder(R.id.scheduleFragment, R.id.subFragment,R.id.noticeFragment)
            .setDrawerLayout(drawerLayout)
            .build()


        setupActionBarWithNavController(navController, appBarConfiguration)

        navigation_view.setupWithNavController(navController)

    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout))
            .apply {
                setStatusBarBackground(R.color.colorPrimaryDark)
            }
    }


}
