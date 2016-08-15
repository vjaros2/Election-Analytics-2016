package edu.illinois.cs.electionanalytics.electionanalytics2016;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        WebView myWebView = (WebView) findViewById(R.id.web_view);
        myWebView.loadUrl("http://electionanalytics.cs.illinois.edu");
        myWebView.setVisibility(View.INVISIBLE);

        Toolbar my_Toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_Toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final MyWebClient myWebViewClient = new MyWebClient();
        myWebView.setWebViewClient(myWebViewClient);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        notification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_screens, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        WebView myWebView = (WebView) findViewById(R.id.web_view);


        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        switch (item.getItemId()) {
            case R.id.action_home:

                myWebView.loadUrl("http://electionanalytics.cs.illinois.edu");
                myWebView.setVisibility(View.INVISIBLE);

                final MyWebClient myWebViewClient = new MyWebClient();
                myWebView.setWebViewClient(myWebViewClient);



                return true;

            case R.id.action_map:

                myWebView.loadUrl("http://electionanalytics.cs.illinois.edu/details.php");
                myWebView.setVisibility(View.INVISIBLE);

                final MapClient MapViewClient = new MapClient(getApplicationContext());
                myWebView.setWebViewClient(MapViewClient);


                return true;

            case R.id.action_method:

                myWebView.loadUrl("http://electionanalytics.cs.illinois.edu/methodology.php");
                myWebView.setVisibility(View.INVISIBLE);

                final MethodClient MethodViewClient = new MethodClient();
                myWebView.setWebViewClient(MethodViewClient);


                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public void notification(){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.donald_trump_transparent)
                        .setContentTitle("2016 Election Updates")
                        .setContentText("Open app for updates!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}



