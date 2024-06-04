package com.example.workwithbroadcastreceiver

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.workwithbroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendSmsButton.setOnClickListener {
            val flag = when{
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
                    PendingIntent.FLAG_MUTABLE
                else -> {
                    PendingIntent.FLAG_MUTABLE
                }
            }

            if (permissionPass()) {
                val sendSmsPendingIntent = PendingIntent.getBroadcast(
                    this, 0, Intent("Send_Sms"), flag
                )
                val receiveSmsPendingIntent = PendingIntent.getBroadcast(
                    this, 0, Intent("Read_Sms"), flag
                )

                val sms = SmsManager.getDefault()
                sms.sendTextMessage(
                    "+1 650 555-6789", null, "Hello Mahdi", sendSmsPendingIntent, null
                )
                registerReceiver(SendSmsReceiver(), IntentFilter("Send_Sms"))
                registerReceiver(ReadSmsReceiver(), IntentFilter("Read_Sms"))
            }
        }
    }

    private fun permissionPass(): Boolean {
        val listPermissionWeNeed = mutableListOf<String>()
        val readSmsPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_SMS
        )
        val sendSmsPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.SEND_SMS
        )
        val receiveSmsPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECEIVE_SMS
        )

        if (readSmsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionWeNeed.add(android.Manifest.permission.READ_SMS)
        }
        if (sendSmsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionWeNeed.add(android.Manifest.permission.SEND_SMS)
        }
        if (receiveSmsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionWeNeed.add(android.Manifest.permission.RECEIVE_SMS)
        }

        if (listPermissionWeNeed.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionWeNeed.toTypedArray(), 9)
            return false
        }
        return true
    }
}