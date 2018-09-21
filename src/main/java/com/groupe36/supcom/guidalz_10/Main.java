package com.groupe36.supcom.guidalz_10;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {

    private FloatingActionButton fab;
    private FloatingActionButton sign_out;
    private Intent i;
    private Intent i2;
    private static final int PICK_CONTACT = 1000;
    private FirebaseAuth mAuth;
    String tel;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=new Intent(this, LocalisationService.class);
        i2=new Intent(this,SupervisorService.class);

        i = new Intent(this, LocalisationService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        // Start the service
        if (!isMyServiceRunning(i.getClass())) {
            startService(i);
        }

        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Profile.class);
                startActivity(intent);
            }
        });
        sign_out = (FloatingActionButton) findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
                Intent i = new Intent(Main.this, LocalisationService.class);
                stopService(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tel=dataSnapshot.child("users").child(userId).child("supervisor_tel").getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final ImageView Button1 = (ImageView ) findViewById(R.id.call_button);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                Log.d("calllll", tel);
                callIntent.setData(Uri.parse("tel:"+ tel));

                if (ActivityCompat.checkSelfPermission(Main.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
        final ImageView  Button2 = (ImageView ) findViewById(R.id.listView_button);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memoryIntent = new Intent(Main.this, memory.class);
                startActivity(memoryIntent);

            }
        });




    }



    @Override
    protected void onDestroy() {
        if (!isMyServiceRunning(i2.getClass())) {
            stopService(i2);
        }
        if (!isMyServiceRunning(i.getClass())) {
            stopService(i);
        }

        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();

    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("isMyServiceRunning?", true+"");
                return true;
            }
        }
        Log.i ("isMyServiceRunning?", false+"");
        return false;
    }


}
