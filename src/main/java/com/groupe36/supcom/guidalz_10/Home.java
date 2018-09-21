package com.groupe36.supcom.guidalz_10;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.groupe36.supcom.guidalz_10.Login.m_status;
import static com.groupe36.supcom.guidalz_10.Login.userId;
import static com.groupe36.supcom.guidalz_10.Login.c_user;

public class Home extends AppCompatActivity {

    private FloatingActionButton fab;
    private FloatingActionButton sign_out;
    private LinearLayout mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });
        sign_out = (FloatingActionButton) findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                Intent i = new Intent(Home.this, LocalisationService.class);
                stopService(i);
                Intent i1 = new Intent(Home.this, SupervisorService.class);
                stopService(i1);
            }
        });
        mem=(LinearLayout)findViewById(R.id.btn_memories_layout);
        if(m_status.equals("Doctor"))
        mem.setVisibility(View.GONE);

        if(m_status.equals("Supervisor")) {
            Intent i2 = new Intent(this, SupervisorService.class);
            // Add extras to the bundle
            i2.putExtra("foo", "bar");
            if (!isMyServiceRunning(i2.getClass())) {
                startService(i2);
            }
        }


    }

    public void GoReminder(View view) {
        Intent intent = new Intent(this,Pill_Reminder.class);
        startActivity(intent);
    }

    public void GoTrack(View view) {
        Intent intent = new Intent(this,Track2.class);
        startActivity(intent);
    }

    public void GoMemories(View view) {
        Intent intent = new Intent(this,Memories.class);
        startActivity(intent);
    }

    public void GoAppointment(View view) {
        Intent intent = new Intent(this,Appointment.class);
        startActivity(intent);
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
