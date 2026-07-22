package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.PermissionRequest;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
public class MainActivity extends Activity {
private ValueCallback<Uri[]> filePathCallback;
private static final int FILE_CHOOSER_REQUEST_CODE = 100;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Force links and redirects to open in the WebView instead of in a browser
       

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
webSettings.setAllowFileAccess(true);
webSettings.setAllowContentAccess(true);
webSettings.setMediaPlaybackRequiresUserGesture(false);

mWebView.setWebChromeClient(new WebChromeClient() {@Override
public boolean onShowFileChooser(
        WebView webView,
        ValueCallback<Uri[]> filePathCallback,
        FileChooserParams fileChooserParams) {

    MainActivity.this.filePathCallback = filePathCallback;

    Intent intent = fileChooserParams.createIntent();
    startActivityForResult(intent, FILE_CHOOSER_REQUEST_CODE);

    return true;
}
    @Override
    public void onPermissionRequest(final PermissionRequest request) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                request.grant(request.getResources());
            }
        });
    }
});

        // Use remote resource
mWebView.setWebViewClient(new WebViewClient());

        // Stop local links and redirects from opening in browser instead of WebView
        mWebView.loadUrl("https://samrat-chat.onrender.com");

        // Use local resource
        // mWebView.loadUrl("file:///android_asset/www/index.html");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == FILE_CHOOSER_REQUEST_CODE) {
        if (filePathCallback == null) return;

        Uri[] results = null;

        if (resultCode == RESULT_OK && data != null) {
            results = new Uri[]{data.getData()};
        }

        filePathCallback.onReceiveValue(results);
        filePathCallback = null;
    }
}
}                            
