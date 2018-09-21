package com.groupe36.supcom.guidalz_10;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.groupe36.supcom.guidalz_10.Profile.user;


public  class ShowMyInfoFragment extends android.support.v4.app.DialogFragment {



    private Button modify_btn;
    private Button ok_btn;
    private TextView name;
    private TextView fname;
    private TextView sex;
    private TextView status;
    private TextView tel;


    private EditText edit;
    int mNum;
    static Intent intent ;

    static ShowMyInfoFragment newInstance(int num) {
        ShowMyInfoFragment f = new ShowMyInfoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_my_info, container, false);

        modify_btn = (Button)v.findViewById(R.id.my_info_modify);
        modify_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                intent = new Intent(getActivity().getApplicationContext(),ModifyInfo.class);
                startActivity(intent);

            }
        });
        ok_btn=(Button)v.findViewById(R.id.my_info_confirm);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tel = (TextView)v.findViewById(R.id.my_tel);
        tel.setText(user.getTel());

       sex= (TextView)v.findViewById(R.id.my_sex);
        sex.setText(user.getSex());

       status= (TextView)v.findViewById(R.id.my_status);
        status.setText(user.getStatus());

        name= (TextView)v.findViewById(R.id.my_name);
        name.setText(user.getName());

        fname = (TextView)v.findViewById(R.id.my_family_name);
        fname.setText(user.getFname());




        return v;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
