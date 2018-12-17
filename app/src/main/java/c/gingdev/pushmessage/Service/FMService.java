package c.gingdev.pushmessage.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import c.gingdev.pushmessage.MainActivity;
import c.gingdev.pushmessage.R;

public class FMService extends FirebaseMessagingService{
    private static final String TAG = FMService.class.getSimpleName();

    @Override
    public void onMessageReceived( RemoteMessage remoteMessage ) {
//        super.onMessageReceived( remoteMessage );
        Log.i( TAG, "onMessageReceived" );

        Map<String, String> data = remoteMessage.getData();
        String title = data.get( "title" );
        String content = data.get( "content" );

        notifyData( title, content );
    }

    private void notifyData(String title, String content) {
        Intent intent = new Intent(this, MainActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        PendingIntent pintent = PendingIntent.getActivity( this, 0, intent, PendingIntent.FLAG_ONE_SHOT );

        String channelId = getString( R.string.default_notification_channel_id );
        NotificationCompat.Builder notify = new NotificationCompat.Builder(this )
                .setAutoCancel( true )
                .setContentTitle( title )
                .setContentText( content )
                .setContentIntent( pintent );

        NotificationManager nm = ( NotificationManager ) getSystemService( Context.NOTIFICATION_SERVICE );

        /**< Oreo 버전 이상은 Notifycation Channel 을 지정해줘야함. >**/
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            NotificationChannel channel = new NotificationChannel( channelId , "FCM Channel", NotificationManager.IMPORTANCE_DEFAULT );
            nm.createNotificationChannel(channel);
        }

        nm.notify(0, notify.build());
    }
}
