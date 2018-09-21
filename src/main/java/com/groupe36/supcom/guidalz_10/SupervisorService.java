package com.groupe36.supcom.guidalz_10;



import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class SupervisorService extends Service {

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    private String userId_reg ;
    private FirebaseAuth mAuth;
    Handler handler;
    float distance=0;
    String p_userId;
    private int interval=10*60000;

    public SupervisorService() {
        super();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.e(TAG, "onCreate");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId_reg=currentUser.getUid();
        handler = new Handler();
        handler.post(runnableCode);

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //TODO do something useful
        return Service.START_NOT_STICKY;
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            Log.d("Handlers", "Called on main thread");
            // Repeat this the same runnable code block again another 2 seconds
            // 'this' is referencing the Runnable object
            readLocation();
            handler.postDelayed(this, interval);
        }
    };

    public float distancecalc(double latitude, double longitude, double e, double f) {
        double d2r = Math.PI / 180;

        double dlong = (longitude - f) * d2r;
        double dlat = (latitude - e) * d2r;
        double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(e * d2r)
                * Math.cos(latitude * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367 * c;
        return (float) d *1000;


    }
    private void readLocation() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                float h_lattitude;
                float h_longitude;
                float p_lattitude;
                float p_longitude;
                int range ;
                h_lattitude=dataSnapshot.child("users").child(userId_reg).child("home").child("lattitude").getValue(float.class);
                h_longitude=dataSnapshot.child("users").child(userId_reg).child("home").child("longitude").getValue(float.class);

                p_userId=dataSnapshot.child("users").child(userId_reg).child("patient").getValue(String.class);
               p_lattitude=dataSnapshot.child("users").child(p_userId).child("lattitude").getValue(float.class);
               p_longitude=dataSnapshot.child("users").child(p_userId).child("longitude").getValue(float.class);
                range=dataSnapshot.child("users").child(userId_reg).child("range").getValue(int.class);
                distance=distancecalc(h_lattitude,h_longitude ,p_lattitude ,p_longitude);
                if (distance > range){
                    //handler.removeCallbacks(runnableCode);
                    interval=3*60000;
                    SupervisorNotification notif =new SupervisorNotification( );
                    notif.notify(SupervisorService.this , "New Alert!!!", 1);

                    //Notification
                }else interval=10*60000;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });
    }



    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        Intent broadcastIntent = new Intent("supcom.groupe36.com.i.am.dying.help.me.supervisor");
        sendBroadcast(broadcastIntent);
        super.onDestroy();
        handler.removeCallbacks(runnableCode);

    }

}
