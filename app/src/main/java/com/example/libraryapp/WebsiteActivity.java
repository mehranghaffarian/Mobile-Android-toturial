package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {
    private WebView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        website = findViewById(R.id.website);
        website.loadUrl("https://quera.ir/dashboard");
        website.setWebViewClient(new WebViewClient());
    }

    //in order to avoid opening another app
    @Override
    public void onBackPressed() {
        if (website.canGoBack())
            website.goBack();
        else
            super.onBackPressed();
    }
}