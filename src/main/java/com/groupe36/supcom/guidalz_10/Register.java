package com.groupe36.supcom.guidalz_10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import android.app.ProgressDialog;
public class Register extends AppCompatActivity {

    public EditText m_email;
    private EditText m_password;
    private EditText m_tel;
    private FirebaseAuth mAuth;
    private static final String TAG = "Login_Activity";
    private FirebaseAuth.AuthStateListener mAuthListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void submet_register(View view) {
        m_email=(EditText)findViewById(R.id.new_email);
        m_password=(EditText)findViewById(R.id.user_password);
        m_tel=(EditText)findViewById(R.id.tel);
        String email=m_email.getText().toString();
        String pass=m_password.getText().toString();
        String tel=m_tel.getText().toString();

         final Intent intent	=	new Intent(this,	My_information.class);
        final ProgressDialog progressDialog = ProgressDialog.show(Register.this, "Please wait...", "Processing...", true);
        if (validateForm()) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();


                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                        // ...
                    }
                }) ;
        }else progressDialog.dismiss();
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

        String password2 = m_tel.getText().toString();
        if  (!password.equals(password2 )) {
            valid =false ;
            m_tel.setError("Must be the same password.");

        }
        if (password.length() <6 ){
            valid =false ;
            m_password.setError("Password should be at least 6 characters");

        }

        return valid;
    }
}
