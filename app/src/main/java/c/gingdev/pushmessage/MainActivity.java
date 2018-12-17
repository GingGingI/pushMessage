package c.gingdev.pushmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import c.gingdev.pushmessage.Service.FMService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getToken();

        if ( FirebaseInstanceId.getInstance().getToken() != null ) {
            Log.d( TAG, "token => "+ FirebaseInstanceId.getInstance().getToken() );
        }
    }
}
