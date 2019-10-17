package com.example.amiguinhos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val FORMULARIO_ADD = 1
    val FORMULARIO_EDIT = 2
    val POSITION_EDIT = -1
    private lateinit var lista : ArrayList<Amigo>
    private lateinit var lvAmigos : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
           val itForm = Intent(this, FormActivity::class.java)
           startActivityForResult(itForm, FORMULARIO_ADD)
        }

        this.lista = arrayListOf()
        this.lvAmigos = findViewById(R.id.lvMainAmiguinhos)
        this.lvAmigos.setOnItemClickListener(ClickList())
        this.lvAmigos.setOnItemLongClickListener(LongClickList())

        this.lista.add(Amigo("Test 1", 1))
        this.lista.add(Amigo("Test 2", 5))
        this.lista.add(Amigo("Test 3", 6))
        this.lista.add(Amigo("Test 4", 3))


        this.lvAmigos.adapter = ArrayAdapter<Amigo>(this, android.R.layout.simple_list_item_1, this.lista)
    }

    fun atualizaLista() {
        (this.lvAmigos.adapter as ArrayAdapter<Amigo>).notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            if(requestCode == FORMULARIO_ADD){
                val amigo = data?.getSerializableExtra("AMIGO") as Amigo
                this.lista.add(amigo)
                this.atualizaLista()
                Log.i("APP_AMIGO", this.lista.toString())

            } else if(requestCode == FORMULARIO_EDIT) {
                val amigo = data?.getSerializableExtra("AMIGO") as Amigo
                val position = data?.getIntExtra("POSITION", POSITION_EDIT)
                this.lista[position] = amigo
                this.atualizaLista()
            }
        }
    }

    inner class ClickList : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val amigo = this@MainActivity.lista[position]
            val itForm = Intent(this@MainActivity, FormActivity::class.java)
            itForm.putExtra("AMIGO", amigo)
            itForm.putExtra("POSITION", position)
            startActivityForResult(itForm, FORMULARIO_EDIT)
        }
    }

    inner class LongClickList : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            this@MainActivity.lista.removeAt(position)
            this@MainActivity.atualizaLista()
            return true
        }
    }
}
