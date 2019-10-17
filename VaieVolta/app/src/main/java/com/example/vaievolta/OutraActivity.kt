package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class OutraActivity : AppCompatActivity() {
    private lateinit var botao : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outra)

        this.botao = findViewById(R.id.btOutraNavegacao)
        this.botao.setOnClickListener({ onClick(it) })

        val mensagem = intent.getStringExtra("MSG_IDA")
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    fun onClick(view : View) {
        val intent = Intent()
        intent.putExtra("MSG_VOLTA", "Voltou boy")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
