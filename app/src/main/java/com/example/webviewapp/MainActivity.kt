package com.example.webviewapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var webView: WebView? = null
    private val mApplicationContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "KotlinApp"
        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient(this)
        val webviewString: String = "<html><h1>Hello, from Webview!</h1>" +
                "<li><a href=\"https://www.google.com\">Google</a></li>" +
                "<li><a href=\"https://www.example.com\">Example</a></li>" +
                "</html>"
        val mimeType: String = "text/html"
        val utfType: String = "UTF-8"
        webView.loadData(webviewString, mimeType, utfType)
    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }

    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: Uri? = request?.url;
            if (url != null) {
                if (url.getHost().toString().endsWith(".google.com")) {
                    return false
                } else {
                    print(0)
                    // launch customTab or Browser here !
                }
            }
            return true
        }
    }
}
