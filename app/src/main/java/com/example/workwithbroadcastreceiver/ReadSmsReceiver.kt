package com.example.workwithbroadcastreceiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.Toast

class ReadSmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var state = ""

        when (resultCode) {
            Activity.RESULT_OK -> state = "پیامک ریافت شد"
            Activity.RESULT_CANCELED -> state = "پیامک ریافت نشد"
        }
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show()
    }
}