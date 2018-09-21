package com.groupe36.supcom.guidalz_10;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText m_email;
    private EditText m_password;

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Login_Activity";
    static String userId ;
    static String m_status;
    static User c_user ;
    private Intent	intent1 ;
    private Intent	intent2;
    private Intent i;
    private Intent i2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();



    }



    public void go_home(View view) {
        m_email=(EditText)findViewById(R.id.Email);
        m_password=(EditText)findViewById(R.id.Password);
        String email=m_email.getText().toString();
        String pass=m_password.getText().toString();



        intent1	=	new	Intent(this,	Home.class);
        intent2	=	new	Intent(this,	Main.class);
        // [START sign_in_with_email]
        final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wait...", "Processing...", true);

        i = new Intent(this, LocalisationService.class);
        if (!isMyServiceRunning(i.getClass())) {
            stopService(i);
        }

        Intent i1 = new Intent(this, SupervisorService.class);
        if (!isMyServiceRunning(i1.getClass())) {
            stopService(i1);
        }
        if (validateForm()){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // Name, email address, and profile photo Url
                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                //Uri photoUrl = user.getPhotoUrl();

                                // Check if user's email is verified
                                boolean emailVerified = user.isEmailVerified();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                String uid = user.getUid();
                            }


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();

                            userId = user.getUid();
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    String status;

                                    c_user=dataSnapshot.child("users").child(userId).getValue(User.class);
                                    status=dataSnapshot.child("users").child(userId).child("status").getValue(String.class);

                                    Toast.makeText(Login.this,"Wellcome",Toast.LENGTH_LONG).show();

                                    String string="Patient";
                                    if(status!=null){
                                        m_status=status;
                                        if (status.equals(string)) {
                                            startActivity(intent2);

                                        }
                                        else {
                                            startActivity(intent1);

                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w(TAG, "Failed to read value.", error.toException());
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            toastMessage("Failed to sign in");
                            progressDialog.dismiss();
                        }

                            // [END_EXCLUDE]
                    }


            }
        );

    }else {
            progressDialog.dismiss();


        }
    }

    public void Register(View view) {
        Intent	intent	=	new	Intent(this,	Register.class);
        // Intent	intent	=	new	Intent(this,	Register.class);
        startActivity(intent);

    }



    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private boolean validateForm() {
        boolean valid = true;


        String email = m_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            m_email.setError("Required.");
            valid = false;

        } else {
            m_email.setError(null);
        }

        String password = m_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            m_password.setError("Required.");
            valid = false;

        } else {
            m_password.setError(null);
        }


        return valid;
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final ProgressDialog progressDialog1 = ProgressDialog.show(Login.this, "Please wait...", "Processing...", true);

        FirebaseUser currentUser = mAuth.getCurrentUser();



        if (currentUser != null){

            userId = currentUser.getUid();

           FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
           intent1	=	new	Intent(this,	Home.class);
           intent2	=	new	Intent(this,	Main.class);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String status;
                    status=dataSnapshot.child("users").child(userId).child("status").getValue(String.class);

                    c_user=dataSnapshot.child("users").child(userId).getValue(User.class);


                    Toast.makeText(Login.this,"Wellcome",Toast.LENGTH_LONG).show();

                    String string="Patient";
                    if(status!=null){
                        m_status=status;

                    if (status.equals(string)) {
                        startActivity(intent2);


                    }
                    else {
                        startActivity(intent1);

                    }


                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });





        }else setContentView(R.layout.activity_login);
        progressDialog1.dismiss();

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

    @Override
    protected void onDestroy() {


        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();

    }

}
