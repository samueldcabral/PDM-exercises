package com.example.cnb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AviaoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getBooleanExtra("state", false)
        if(status) {
            Toast.makeText(context, "Modo aviao foi ligado", Toast.LENGTH_LONG ).show()
        }else {
            Toast.makeText(context, "Modo aviao foi desligado", Toast.LENGTH_LONG ).show()
        }
    }
}
