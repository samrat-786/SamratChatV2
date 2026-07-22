package com.example.app;

import android.webkit.WebView;
import android.webkit.WebViewClient;
mWebView.setWebViewClient(new MyAppWebViewClient());

    @Override
public boolean shouldOverrideUrlLoading(WebView view, String url) {
    view.loadUrl(url);
    return true;
}
}
