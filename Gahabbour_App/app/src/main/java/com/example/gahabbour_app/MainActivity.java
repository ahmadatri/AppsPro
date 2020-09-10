package com.example.gahabbour_app;

import android.graphics.Bitmap;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

     WebView webview;
//     WebView webview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview=(WebView)findViewById(R.id.webview);
//        webview2=(WebView)findViewById(R.id.webview2);

        WebSettings webSettings=webview.getSettings();
//        WebSettings webSettings2=webview2.getSettings();
        webview.loadUrl("https://gisinter.online/ghabbour/app_portal/");
//        webview2.loadUrl("https://gisinter.online/ghabbour/app_portal/login.php");

        webview.setWebViewClient(new WebViewClient());
//        webview2.setWebViewClient(new WebViewClient());

        webSettings.setJavaScriptEnabled(true);
//        webSettings2.setJavaScriptEnabled(true);


    }

    public class myWebClient extends WebViewClient{
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    public void onBackPressed(){
            if(webview.canGoBack()){
                webview.goBack();
            }
            else{
                super.onBackPressed();
            }

//        if(webview2.canGoBack()){
//            webview2.goBack();
//        }
//        else{
//            super.onBackPressed();
//        }
    }
}