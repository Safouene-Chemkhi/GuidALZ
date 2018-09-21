package com.groupe36.supcom.guidalz_10;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Add_medecine extends AppCompatActivity {

    private Button confirm;
    private Button cancel ;
    private Button choose;
    private TextView hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medecine);
        confirm=(Button)findViewById(R.id.confirm_add);
        cancel=(Button)findViewById(R.id.cancel_add);
        choose=(Button)findViewById(R.id.choose_btn);
        hour=(TextView) findViewById(R.id.time_text);

        confirm.setOnClickListener(new View.OnClickListener()
                                             {
                                                 @Override
                                                 public void onClick(View v)
                                                 {

                                                 }
                                             }

        );

        cancel.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {
                                           finish();
                                       }
                                   }

        );

        choose.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {

                                           DialogFragment newFragment	=	new	TimePickerFragment();
                                           newFragment.show(getSupportFragmentManager(),getString(R.string.time_picker));

                                       }
                                   }

        );
    }
}
