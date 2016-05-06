package com.herprogramacion.uml.otros;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.herprogramacion.uml.R;

public class Radio_Layaout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio__layaout);


        String url = "http://www.emisoras.com.mx/";
        WebView view = (WebView) this.findViewById(R.id.web);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);



    }
}
