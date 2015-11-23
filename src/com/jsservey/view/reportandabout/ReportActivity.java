package com.jsservey.view.reportandabout;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.abx.jsservey.R;
import com.jsservey.view.BaseActivity;

public class ReportActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.webview_layout);
		//handleHomeClick(this.findViewById(android.R.id.content));
		WebView webView = (WebView) findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.google.com");
	}
}

