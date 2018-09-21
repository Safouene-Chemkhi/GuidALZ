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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class My_information extends AppCompatActivity {

    private EditText m_name;
    private EditText m_fname;
    private EditText m_tel;
    private RadioGroup m_status;
    private RadioGroup m_sex;
    private FirebaseAuth mAuth;




    static String userId;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    public String status ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        m_name=(EditText)findViewById(R.id.name);
        m_fname=(EditText)findViewById(R.id.fname);
        m_tel=(EditText)findViewById(R.id.telnumber);

        m_status = (RadioGroup) findViewById(R.id.status);
        m_sex = (RadioGroup) findViewById(R.id.sex);

        m_status.check(R.id.supervisorbtn);
        m_sex.check(R.id.malebtn);






    }

    public void send_info(View view) {


        String name=m_name.getText().toString();
        String fname=m_fname.getText().toString();
        String tel=m_tel.getText().toString();
        String sex ;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser userAuth = mAuth.getCurrentUser();


        String email ;
        email=userAuth.getEmail();

        int checkedId=m_status.getCheckedRadioButtonId();

        if(checkedId == R.id.supervisorbtn) {
            status = "Supervisor" ;
        }else if(checkedId == R.id.doctorbtn){
            status = "Doctor";
        } else {
            status="Patient";
        }


        int checkedId2=m_sex.getCheckedRadioButtonId();
        if(checkedId == R.id.malebtn) {
            sex="Male" ;
        } else {
            sex="Female";
        }




        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        userId = userAuth.getUid();

        User user = new User(name, fname, email , status ,sex ,tel);


        mFirebaseDatabase.child(userId).setValue(user);
        mFirebaseDatabase.child(userId).child("home").child("lattitude").setValue(0);
        mFirebaseDatabase.child(userId).child("home").child("longitude").setValue(0);

        Intent intent1 = new Intent(this,Home.class);
        Intent intent2 = new Intent(this,Main.class);

        if (status.equals("Patient")) {
            startActivity(intent2);
            Intent i = new Intent(this, LocalisationService.class);
            // Add extras to the bundle
            i.putExtra("foo", "bar");
            // Start the service
            if (!isMyServiceRunning(i.getClass())) {
                startService(i);
            }
            mFirebaseDatabase.child(userId).child("supervisor_tel").setValue(0);
        }
        else {
            startActivity(intent1);
            if(status.equals("Supervisor")) {
                Intent i2 = new Intent(this, SupervisorService.class);
                // Add extras to the bundle
                i2.putExtra("foo", "bar");
                if (!isMyServiceRunning(i2.getClass())) {
                    startService(i2);
                }
            }
        }




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
