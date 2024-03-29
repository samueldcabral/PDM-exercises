package com.example.ifpbweb

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btOlhe : Button
    private lateinit var wvIFPB : WebView
    val IFPB = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btOlhe = findViewById(R.id.btOlhe)
        this.wvIFPB = findViewById(R.id.wvIFPB)
        this.wvIFPB.webViewClient = MyWebViewClient()

        this.btOlhe.setOnClickListener(OnClickBotao())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == IFPB) {
                val url = data?.getStringExtra("URL")
                this.wvIFPB.loadUrl(url)
            }
        }
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
//            val url = "http://www.ifpb.edu.br/"
//            this@MainActivity.wvIFPB.loadUrl(url)
            val itIFPB = Intent("IFPB")
            startActivityForResult(itIFPB, IFPB)

        }
    }

    inner class MyWebViewClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String?
        ): Boolean {
            if(Uri.parse(url).host == "www.github.com" ){
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply{
                    startActivity(this)
                }
            }else {
                return false
            }
            return true
        }
    }
}

