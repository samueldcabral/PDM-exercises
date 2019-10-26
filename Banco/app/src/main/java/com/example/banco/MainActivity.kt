package com.example.banco

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
    val FORM_INSERIR = 1
    val FORM_EDITAR = 2
    var LAST_GUY = -1
    private lateinit var lvPessoas : ListView
    private lateinit var dao : PessoaDAO
    private lateinit var listaPessoas : ArrayList<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.dao = PessoaDAO(this)
        this.listaPessoas = this.dao.get()

        this.lvPessoas = findViewById(R.id.lvMainPessoas)
        this.lvPessoas.adapter = PessoaAdapter(this, this.listaPessoas)

        this.lvPessoas.setOnItemClickListener(OnClickLista())
        this.lvPessoas.setOnItemLongClickListener(OnLongClickLista())


        fab.setOnClickListener { view ->
            val itForm = Intent(this, FormActivity::class.java)
            startActivityForResult(itForm, FORM_INSERIR)
        }
    }

    fun atualizar(){
        this.listaPessoas.clear()
        this.listaPessoas.addAll(this.dao.get())
        (this.lvPessoas.adapter as PessoaAdapter).update()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == FORM_INSERIR) {
                val p = data?.getSerializableExtra("PESSOA") as Pessoa
                Log.i("APP_BANCO", "Pessoa: ${p.nome}")

                this.dao.insert(p)
                Log.e("APP_BANCO", this.dao.get().toString())

                this.atualizar()
            }else if(requestCode == FORM_EDITAR) {
                val p = data?.getSerializableExtra("PESSOA") as Pessoa
                this.dao.update(LAST_GUY, p)
                this.LAST_GUY = -1
                this.atualizar()
            }
        }
    }

    inner class OnClickLista : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val pessoa = this@MainActivity.listaPessoas.get(position) as Pessoa
            val itForm = Intent(this@MainActivity, FormActivity::class.java)
            itForm.putExtra("PESSOA", pessoa)
            this@MainActivity.LAST_GUY = pessoa.id
            startActivityForResult(itForm, FORM_EDITAR)
        }
    }

    inner class OnLongClickLista : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            val pessoa = this@MainActivity.listaPessoas.get(position) as Pessoa
            this@MainActivity.dao.delete(pessoa.id)
            this@MainActivity.atualizar()
            return true
        }
    }

}
