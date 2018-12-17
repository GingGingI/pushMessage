package c.gingdev.pushmessage.Service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FIIService extends FirebaseInstanceIdService {
    private static final String TAG = FIIService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d( TAG, "token => "+ token);
    }
}
