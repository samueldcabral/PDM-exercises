package com.example.servicos

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var botaoHtml : Button
    private lateinit var botaoDiscar : Button
    private lateinit var botaoLigar : Button
    private lateinit var botaoEmail : Button
    private lateinit var botaoSms : Button
    private lateinit var botaoCompartilhar : Button
    private lateinit var botaoPonto : Button
    private lateinit var botaoRota : Button
    private lateinit var botaoYoutube : Button
    private lateinit var botaoFoto : Button
    private lateinit var fotoImg : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.botaoHtml = findViewById(R.id.btMainHtml)
        this.botaoDiscar = findViewById(R.id.btMainDiscar)
        this.botaoLigar = findViewById(R.id.btMainLigar)
        this.botaoEmail = findViewById(R.id.btMainEmail)
        this.botaoSms = findViewById(R.id.btMainSms)
        this.botaoCompartilhar = findViewById(R.id.btMainCompartilhar)
        this.botaoPonto = findViewById(R.id.btMainPonto)
        this.botaoRota = findViewById(R.id.btMainRota)
        this.botaoYoutube = findViewById(R.id.btMainYoutube)
        this.botaoFoto = findViewById(R.id.btMainFoto)
        this.fotoImg = findViewById(R.id.ivImagem)

        this.botaoHtml.setOnClickListener(this::onClickHtml)
        this.botaoDiscar.setOnClickListener(this::onClickDiscar)
        this.botaoLigar.setOnClickListener(this::onClickLigar)
        this.botaoEmail.setOnClickListener(this::onClickEmail)
        this.botaoSms.setOnClickListener(this::onClickSms)
        this.botaoCompartilhar.setOnClickListener(this::onClickCompartilhar)
        this.botaoPonto.setOnClickListener(this::onClickPonto)
        this.botaoRota.setOnClickListener(this::onClickRota)
        this.botaoYoutube.setOnClickListener(this::onClickYoutube)
        this.botaoFoto.setOnClickListener(this::onClickFoto)
    }

    fun onClickHtml(view : View) {
        Log.i("APP_BTN", "Clicou HTML")
        val uri = Uri.parse("http://9gag.com")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun onClickDiscar(view : View) {
        Log.i("APP_BTN", "Clicou discar")
        val uri = Uri.parse("tel:997148733")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }

    fun onClickLigar(view : View) {
        Log.i("APP_BTN", "Clicou ligar")
        val uri = Uri.parse("tel:04183999391645")
        val call = android.Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED
        val intent = Intent(Intent.ACTION_CALL, uri)

        if(ContextCompat.checkSelfPermission(this, call) == granted){
            Log.i("APP_BTN", "permissao garantida")
            startActivity(intent)
        }else{
            Log.i("APP_BTN", "Falha na ligacao")
        }
    }

    fun onClickEmail(view : View) {
        Log.i("APP_BTN", "Clicou email")
        val uri = Uri.parse("mailto:samueldcabral@gmail.com")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("EXTRA_SUBJECT", "Samuel doidao")
        intent.putExtra("EXTRA_TEXT", "Lorem ipsum dolor")
        startActivity(intent)
    }

    fun onClickSms(view : View) {
        Log.i("APP_BTN", "Clicou sms")
        val uri = Uri.parse("sms:997148733")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "Samuel ta mandando email")
        startActivity(intent)
    }

    fun onClickCompartilhar(view : View) {
        Log.i("APP_BTN", "Clicou compartilhar")

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, "VAI compartilhar meu chegado")
        startActivity(Intent.createChooser(intent, "Compartilharer"))
    }

    fun onClickPonto(view : View) {
        Log.i("APP_BTN", "Clicou ponto")
        val uri = Uri.parse("geo:40.798701,-73.966942")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun onClickRota(view : View) {
        Log.i("APP_BTN", "Clicou rota")
        val origem = "40.798701,-73.966942"
        val destino = "40.757951,-73.985581"
        val url = "http://maps.google.com/maps"

        val uri = Uri.parse("$url?f=&saddr=$origem+&daddr=$destino")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun onClickYoutube(view : View) {
        Log.i("APP_BTN", "Clicou youtube")
        val uri = Uri.parse("vnd.youtube://XxCBgsmmUOs")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun onClickFoto(view : View) {
        Log.i("APP_BTN", "Clicou foto")

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivity(intent)
        startActivityForResult(intent, 12);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 12){
            if(resultCode == Activity.RESULT_OK){
                //
                val imgBitmap = data?.extras?.get("data") as Bitmap
                fotoImg.setImageBitmap(imgBitmap)
            }
        }
    }
}
