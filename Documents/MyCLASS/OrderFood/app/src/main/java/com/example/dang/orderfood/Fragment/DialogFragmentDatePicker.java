package com.example.dang.orderfood.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dang.orderfood.R;

import java.util.Calendar;

/**
 * Created by DANG on 8/6/2016.
 */
public class DialogFragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar  = Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date= calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,date);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dateOfMonth) {
        EditText editText = (EditText) getActivity().findViewById(R.id.editNgaySinh);
        String ngaysinh= dateOfMonth +"/"+(monthOfYear+1)+"/"+year;
        editText.setText(ngaysinh);
    }
}
