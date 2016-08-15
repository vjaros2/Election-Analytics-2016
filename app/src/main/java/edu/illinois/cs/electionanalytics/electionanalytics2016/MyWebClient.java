package edu.illinois.cs.electionanalytics.electionanalytics2016;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by vjaros2 on 5/19/2016.
 */
public class MyWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByTagName('h1')[1].style.display='none'; " +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByTagName('p')[0].style.display='none'; " +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByTagName('p')[1].style.display='none'; " +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByTagName('form')[0].style.display='none'; " +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('mainNav navbar navbar-default')[0].style.display='none'; " +
                "})()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('ea-banner')[0].style.display='none'; " +
                "})()");
        
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('ea-map')[0].style.display='none'; " +
                "})()");
        view.setVisibility(view.VISIBLE);
    }
}
