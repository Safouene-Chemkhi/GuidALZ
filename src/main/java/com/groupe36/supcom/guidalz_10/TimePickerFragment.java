package com.groupe36.supcom.guidalz_10;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment 	implements	TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int	minute;
    int	hour;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle	savedInstanceState)	{
        //	Use	the	current	time	as	the	default	values	for	the	picker.
        final Calendar c	=	Calendar.getInstance();
        hour	=	c.get(Calendar.HOUR_OF_DAY);
        minute	=	c.get(Calendar.MINUTE);
        //	Create	a	new	instance	of	TimePickerDialog	and	return	it.
        return	new TimePickerDialog(getActivity(),	this,	hour,	minute,	DateFormat.is24HourFormat(getActivity()));
    }
    public	void	onTimeSet(TimePicker view, int	hourOfDay, int	minute)	{

        //	Do	something	with	the	time	chosen	by	the	user.
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }








}
