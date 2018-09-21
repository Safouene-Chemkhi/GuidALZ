package com.groupe36.supcom.guidalz_10;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Pill_Reminder extends AppCompatActivity {
    private EditText mLocationEditText;
    private TextView eReminderTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill__reminder);
    }

    public void add_pill(final View view) {
        Intent intent=new Intent(this,Add_medecine.class);
        startActivity(intent);

        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        FragmentManager fm = getSupportFragmentManager();

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = ShowMyInfoFragment.newInstance(1);
        newFragment.show(fm, "dialog");*/


       /* AlertDialog.Builder	myAlertBuilder	=	new	AlertDialog.Builder(Pill_Reminder.this);
        //	Set	the	dialog title.
        myAlertBuilder.setTitle("Add Medecine");
        //	Set	the	dialog message.
        myAlertBuilder.setMessage("Add the name of the medecine and choose a time:");

        final EditText input = new EditText(Pill_Reminder.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        myAlertBuilder.setView(input);

        //	Add	the	buttons.
        myAlertBuilder.setNeutralButton("CHOOSE HOUR",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{
                DialogFragment newFragment	=	new	TimePickerFragment();
                newFragment.show(getSupportFragmentManager(),getString(R.string.time_picker));
                //	User	clicked	OK	button.




            }
        });

        myAlertBuilder.setNegativeButton("Cancel",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface	dialog,	int	which)	{
                //	User	cancelled	the	dialog.
                Toast.makeText(getApplicationContext(),	"Pressed	Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.setPositiveButton("CONFIRM",	new	DialogInterface.OnClickListener()	{
            public	void	onClick(DialogInterface dialog, int	which)	{

                //	User	clicked	OK	button.
                Toast.makeText(getApplicationContext(),	"Medecine added", Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.show();*/
    }

}
