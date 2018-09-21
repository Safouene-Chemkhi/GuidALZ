package com.groupe36.supcom.guidalz_10;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;



public class DatePickerFragment extends DialogFragment 	implements	DatePickerDialog.OnDateSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    @NonNull
    @Override
    public	Dialog	onCreateDialog(Bundle	savedInstanceState)	{
        //	Use	the	current	date	as	the	default	date	in	the	picker.
        final	Calendar	c	=	Calendar.getInstance();
        int	year	=	c.get(Calendar.YEAR);
        int	month	=	c.get(Calendar.MONTH);
        int	day	=	c.get(Calendar.DAY_OF_MONTH);
        //	Create	a	new	instance	of	DatePickerDialog	and	return	it.
        return	new	DatePickerDialog(getActivity(),	this,	year,	month,	day);
    }
        public	void	onDateSet(DatePicker view, int	year, int	month, int	day)	{
        //	Do	something	with	the	date	chosen	by	the	user.

        }
            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



}
