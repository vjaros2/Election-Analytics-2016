package edu.illinois.cs.electionanalytics.electionanalytics2016;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    final String initialToken;

    public MyFirebaseInstanceIdService() {
        this.initialToken = FirebaseInstanceId.getInstance().getToken();
    }

    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN!!!", "Refreshed token: " + this.initialToken);
        sendRegistrationToServer(refreshedToken);
    }

    public static void sendRegistrationToServer(String refreshedToken) {
    }
}
