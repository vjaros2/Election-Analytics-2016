package edu.illinois.cs.electionanalytics.electionanalytics2016;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by vjaros2 on 5/19/2016.
 */
public class MethodClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:(function() { " +
                "var divsToHide = document.getElementsByTagName('iframe');" +
                "for(var i = 0; i < divsToHide.length; i++){ " +
                "if(true){" +
                "\tdivsToHide[i].style.display = 'none'; }}" +
                "})()");
        view.setVisibility(view.VISIBLE);
    }
}
