package com.example.diadascrianas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class nextActivity : AppCompatActivity() {
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button
    private lateinit var etNome : EditText
    private lateinit var etPresente : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        this.btSalvar = findViewById(R.id.btFormSalvar)
        this.btCancelar = findViewById(R.id.btFormCancelar)
        this.etNome = findViewById(R.id.etFormNome)
        this.etPresente = findViewById(R.id.etFormPresente)

        this.btSalvar.setOnClickListener({ salvarForm(it) })
        this.btCancelar.setOnClickListener({ cancelarForm(it) })
    }

    fun salvarForm(view : View) {
        val nome = this.etNome.text.toString()
        val presente = this.etPresente.text.toString()
        val pedido = Pedido(nome, presente)

        val itResposta = Intent()
        itResposta.putExtra("NOME", nome)
        itResposta.putExtra("PRESENTE", presente)
        itResposta.putExtra("PEDIDO", pedido)



        setResult(Activity.RESULT_OK, itResposta)
        finish()
    }

    fun cancelarForm(view : View) {
        finish()
    }
}
