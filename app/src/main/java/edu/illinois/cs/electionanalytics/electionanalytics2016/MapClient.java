package edu.illinois.cs.electionanalytics.electionanalytics2016;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;

/**
 * Created by vjaros2 on 5/19/2016.
 */
public class MapClient extends WebViewClient {
    private Context mCtx;
    public MapClient(Context context) {
        super();
        mCtx = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.getContext().startActivity(
                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // hides all  elements.

        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByTagName('header');" +
                "divsToHide[0].style.display = 'none';" +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementById('election-overview');" +
                "divsToHide.style.display = 'none';" +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementById('sticky-footer-footer');" +
                "divsToHide.style.display = 'none';" +
                "})()");

        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByTagName('h1');" +
                "for(var i = 0; i < divsToHide.length; i++){ " +
                "if(true){" +
                "\tdivsToHide[i].style.display = 'none'; }}" +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByTagName('nav');" +
                "for(var i = 0; i < divsToHide.length; i++){ " +
                "if(true){" +
                "\tdivsToHide[i].style.display = 'none'; }}" +
                "})()");

        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByClassName('clearfix module');" +
                "for(var i = 0; i < divsToHide.length; i++){ " +
                "if(true){" +
                "\tdivsToHide[i].style.display = 'none'; }}" +
                "})()");

        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByTagName('iframe');" +
                "for(var i = 0; i < divsToHide.length; i++){ " +
                "if(true){" +
                "\tdivsToHide[i].style.display = 'none'; }}" +
                "})()");

//        view.loadUrl("javascript:(function() {\n" +
//                "    var chart = document.getElementById('highcharts-0');" +
//                "    var w = '100px'; " +
//                "    var h = '100px'; " +
//                "    chart.style.width = w; " +
//                "    chart.style.height = h; " +
//                "})()");

        injectCSS(view, url);




        view.setVisibility(view.VISIBLE);
    }

    private void injectCSS(WebView view, String url) {
        try {
            InputStream inputStream = mCtx.getResources().openRawResource(R.raw._modules);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            view.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementById('map');" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
            Log.w("EXEC,", "exec!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
