package com.jsservey.view.reportandabout;

import com.abx.jsservey.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ReportActivity extends Activity {private WebView webview;
private static final String TAG = "Main";
private ProgressDialog progressBar;  


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);

    setContentView(R.layout.webview_layout);

    this.webview = (WebView)findViewById(R.id.web_view);

    WebSettings settings = webview.getSettings();
    settings.setJavaScriptEnabled(true);
    webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

    final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

    progressBar = ProgressDialog.show(ReportActivity.this, "", "Loading...");

    webview.setWebViewClient(new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "Processing webview url click...");
            view.loadUrl(url);
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            Log.i(TAG, "Finished loading URL: " +url);
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e(TAG, "Error: " + description);
            Toast.makeText(getApplicationContext(), "Unable to load content! " + description, Toast.LENGTH_SHORT).show();
            alertDialog.setTitle("Error");
            alertDialog.setMessage(description);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            alertDialog.show();
        }
    });
    webview.loadUrl("http://www.google.com");
}}

