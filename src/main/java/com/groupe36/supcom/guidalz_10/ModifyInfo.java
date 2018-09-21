package com.groupe36.supcom.guidalz_10;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.groupe36.supcom.guidalz_10.Profile.userId;

public class ModifyInfo extends AppCompatActivity {

    private EditText m_name;
    private EditText m_fname;
    private EditText m_tel;
    private RadioGroup m_status;
    private RadioGroup m_sex;
    private FirebaseAuth mAuth;





    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    public String status ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        m_name=(EditText)findViewById(R.id.name1);
        m_fname=(EditText)findViewById(R.id.fname1);
        m_tel=(EditText)findViewById(R.id.telnumber1);

        m_status = (RadioGroup) findViewById(R.id.status1);
        m_sex = (RadioGroup) findViewById(R.id.sex1);

        m_status.check(R.id.supervisorbtn1);
        m_sex.check(R.id.malebtn1);

        Intent i = new Intent(this, LocalisationService.class);
        i.putExtra("foo", "bar");
        Intent i2 = new Intent(this, SupervisorService.class);
        i2.putExtra("foo", "bar");


        if (!isMyServiceRunning(i.getClass())) {
            stopService(i);
        }
        if (!isMyServiceRunning(i2.getClass())) {
            stopService(i2);
        }




    }

    public void send_info1(View view) {


        String name=m_name.getText().toString();
        String fname=m_fname.getText().toString();
        String tel=m_tel.getText().toString();
        String sex ;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser userAuth = mAuth.getCurrentUser();
        Intent i = new Intent(this, LocalisationService.class);
        i.putExtra("foo", "bar");
        Intent i2 = new Intent(this, SupervisorService.class);
        i2.putExtra("foo", "bar");

        String email ;
        email=userAuth.getEmail();

        int checkedId=m_status.getCheckedRadioButtonId();

        if(checkedId == R.id.supervisorbtn1) {
            status = "Supervisor" ;
            startService(i);
        }else if(checkedId == R.id.doctorbtn1){
            status = "Doctor";

            // Add extras to the bundle

        } else {
            status="Patient";

            startService(i2);
        }


        int checkedId2=m_sex.getCheckedRadioButtonId();
        if(checkedId == R.id.malebtn1) {
            sex="Male" ;
        } else {
            sex="Female";
        }

        final User user = new User(name, fname, email , status ,sex ,tel);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference();
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("users").child(userId).setValue(user);
                dataSnapshot.getRef().child("users").child(userId).child("home").child("lattitude").setValue(0);
                dataSnapshot.getRef().child("users").child(userId).child("home").child("longitude").setValue(0);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });

        Intent intent = new Intent(this,Login.class);



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
