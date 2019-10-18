package com.example.ifpb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var lvCampi : ListView
    private lateinit var ifpb : IFPB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lvCampi = findViewById(R.id.lvCampi)
        this.ifpb = IFPB()
        this.cadastraCampiIFPB()
        this.lvCampi.adapter = ArrayAdapter<Campus>(this, android.R.layout.simple_list_item_1, this.ifpb.get())
        this.lvCampi.setOnItemClickListener(OnClickLista())
    }

    fun cadastraCampiIFPB(){
        this.ifpb.add(Campus("Joao pessoa", "http://www.ifpb.edu.br/joaopessoa"))
        this.ifpb.add(Campus("Mangabeira", "http://www.ifpb.edu.br/mangabeira"))
        this.ifpb.add(Campus("Princesa Isabel", "http://www.ifpb.edu.br/princesaisabel"))
        this.ifpb.add(Campus("Santa Rita", "http://www.ifpb.edu.br/santarita"))
        this.ifpb.add(Campus("github", "http://www.github.com"))
        this.ifpb.add(Campus("facebook", "http://www.facebook.com"))
    }

    inner class OnClickLista : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val campus = this@MainActivity.ifpb.get(position)
            val itResposta = Intent()
            itResposta.putExtra("URL", campus.url)
            setResult(Activity.RESULT_OK, itResposta)
            finish()
        }
    }
}
