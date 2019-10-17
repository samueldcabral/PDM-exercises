package com.example.expobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var numeros:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.numeros = findViewById(R.id.tvNumeros)
        this.numeros.text = this.megasena().toString()

    }

    fun megasena():List<Int> {
        var lista = mutableSetOf<Int>()
        val random = Random()

        while(lista.size < 6) {
            lista.add(random.nextInt(60) + 1)
        }
        return lista.toList()
    }

    fun novoJogo(view : View){
        this.numeros.text = this.megasena().toString()
    }
}
