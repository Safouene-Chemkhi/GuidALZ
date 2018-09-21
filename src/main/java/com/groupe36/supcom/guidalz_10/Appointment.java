package com.groupe36.supcom.guidalz_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appointment extends AppCompatActivity {
    List<String> td;
    FirebaseAuth mAuth;
    String userId;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        mListView = (ListView) findViewById(R.id.listView);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();
        Firebase.setAndroidContext(this);
        Firebase mFirebaseRef = new Firebase("https://guidalz-30aec.firebaseio.com/");

        mFirebaseRef.child("users").child(userId).child("rdv").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot result){
                List<Rdv> lst = new ArrayList<Rdv>(); // Result will be holded Here
                for(DataSnapshot dsp : result.getChildren()){
                    lst.add(dsp.getValue(Rdv.class)); //add result into array list

                }
                //NOW YOU HAVE ARRAYLIST WHICH HOLD RESULTS

                Rdv_cellAdapter adapter = new Rdv_cellAdapter(Appointment.this, lst);
                mListView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void add_appointment(View view) {

    }
}
