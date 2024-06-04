package com.example.workwithbroadcastreceiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.Toast

class SendSmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var state = ""

        when (resultCode) {
            Activity.RESULT_OK -> state = "پیامک با موفقیت ارسال شد"
            SmsManager.RESULT_ERROR_GENERIC_FAILURE -> state = "خطا رخ داد"
            SmsManager.RESULT_ERROR_RADIO_OFF -> state = "شبکه در دسترس نیست"
            SmsManager.RESULT_ERROR_NO_SERVICE -> state = "سرویس در دسترس نیست"
        }
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show()
    }
}