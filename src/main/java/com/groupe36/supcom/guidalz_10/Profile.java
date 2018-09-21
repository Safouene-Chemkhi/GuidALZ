package com.groupe36.supcom.guidalz_10;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    static User user;
    static String userId;
    String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Firebase.setAndroidContext(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user=dataSnapshot.child("users").child(userId).getValue(User.class);
                if(user.getStatus().equals("Patient")){
                    LinearLayout l = (LinearLayout)findViewById(R.id.patient);
                    l.setVisibility(View.GONE);
                }else
                {
                    String patientid=dataSnapshot.child("users").child(userId).child("patient").getValue(String.class);
                    patient=dataSnapshot.child("users").child(patientid).child("email").getValue(String.class);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void show_my_information(View view) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        FragmentManager fm = getSupportFragmentManager();

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = ShowMyInfoFragment.newInstance(7);
        newFragment.show(fm, "dialog");

    }

    public void change_password(View view) {
        AlertDialog.Builder	myAlertBuilder	=	new
                AlertDialog.Builder(Profile.this);
        //	Set	the	dialog title.
        myAlertBuilder.setTitle("My Information");
        //	Set	the	dialog message.
        myAlertBuilder.setMessage("Name:");
        myAlertBuilder.setPositiveButton("OK",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{

                //	User	clicked	OK	button.
                Toast.makeText(getApplicationContext(),	"Modification saved", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNeutralButton("Change password",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{
                AlertDialog.Builder change_pass = new AlertDialog.Builder(Profile.this);
                change_pass.setTitle("Change password");
                change_pass.setMessage("Type your new password:");

                final EditText input = new EditText(Profile.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                change_pass.setView(input);
                change_pass.setPositiveButton("OK",	new	DialogInterface.OnClickListener()	{
                    public	void	onClick(DialogInterface dialog, int	which)	{

                        //	User	clicked	OK	button.
                        Toast.makeText(getApplicationContext(),	"Modification saved", Toast.LENGTH_SHORT).show();
                    }
                });
                change_pass.show();
                //	User	clicked	OK	button.
                Toast.makeText(getApplicationContext(),	"My Account", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.show();
    }

    public void show_settings(View view) {
        AlertDialog.Builder	myAlertBuilder	=	new
                AlertDialog.Builder(Profile.this);
        myAlertBuilder.setTitle("Settings");
        //	Set	the	dialog message.
        myAlertBuilder.setMessage("Name:");
        myAlertBuilder.show();
    }

    public void show_patient_account(View view) {
        AlertDialog.Builder	myAlertBuilder	=	new
                AlertDialog.Builder(Profile.this);
        myAlertBuilder.setTitle("Patient Account");
        //	Set	the	dialog message.
        if(patient!="default_patient") myAlertBuilder.setMessage(patient);
        else myAlertBuilder.setMessage("Not set");
        myAlertBuilder.setPositiveButton("OK",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{

                //	User	clicked	OK	button.
                Toast.makeText(getApplicationContext(),	"Saved", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNeutralButton("Change",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{
                    AlertDialog.Builder change=new AlertDialog.Builder(Profile.this);
                    change.setTitle("Patient Email");
                    change.setMessage("Type patient email");
                final EditText input = new EditText(Profile.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                change.setView(input);

                change.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            String m =input.getText().toString();

                        lol(m);
                    }
                });
                change.show();


            }
        });

            myAlertBuilder.show();
    }
    public void lol(String s){


        final Firebase ref = new Firebase("https://guidalz-30aec.firebaseio.com/");

        Query query = ref.child("users").orderByChild("email").equalTo(s);
        query.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                //

                for(com.firebase.client.DataSnapshot ds: dataSnapshot.getChildren()){
                    String v=ds.getKey();
                    Log.d("aaaaaaaaa", v );

                DatabaseReference mFirebaseDatabase;
                FirebaseDatabase mFirebaseInstance;
                mFirebaseInstance = FirebaseDatabase.getInstance();

                // get reference to 'users' node
                mFirebaseDatabase = mFirebaseInstance.getReference("users");

                mFirebaseDatabase.child(userId).child("patient").setValue(v);
                mFirebaseDatabase.child(v).child("supervisor_tel").setValue(user.getTel());
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public void show_patient_information(View view) {
        AlertDialog.Builder	myAlertBuilder	=	new
                AlertDialog.Builder(Profile.this);
        myAlertBuilder.setTitle("Patient");
        //	Set	the	dialog message.
        myAlertBuilder.setMessage("Name:");
        myAlertBuilder.setPositiveButton("OK",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{

                //	User	clicked	OK	button.
                Toast.makeText(getApplicationContext(),	"My Account", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.show();
    }
}
