package com.example.secondapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String FIRST_NAME = "first_name";
    private static final String BIRTHDAY = "birthday";
    private static final String BIRTH_DEPARTMENT = "birth_department";
    private static final String BIRTH_CITY = "birth_city";

    private String name;
    private String firstName;
    private String birthday;
    private int indexBirthDepartment;
    private String birthCity;


    private TextView spinnerLabelTextView;
    private Spinner departmentBirthSpinner;
    private DatePicker birthdayDatePicker;
    private Button confirm;


    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter birthday.
     * @param param2 Parameter birth_department.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String paramName, String paramFirstName, String paramBirthday, int paramIndexBirthDepartment, String paramBirthCity) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(NAME, paramName);
        args.putString(FIRST_NAME, paramFirstName);
        args.putString(BIRTHDAY, paramBirthday);
        args.putInt(BIRTH_DEPARTMENT, paramIndexBirthDepartment);
        args.putString(BIRTH_CITY, paramBirthCity);
        fragment.setArguments(args);
        return fragment;
    }
    public interface OnUpdateContentListenerDate {
        void setContextDate(String returnName, String returnFirstName, String returnBirthday, int returnBirthDepartment, String returnBirthCity);
    }


    EditFragment.OnUpdateContentListenerDate mCallbackDate;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbackDate = (EditFragment.OnUpdateContentListenerDate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            firstName = getArguments().getString(FIRST_NAME);
            birthCity = getArguments().getString(BIRTH_CITY);
            birthday = getArguments().getString(BIRTHDAY);
            indexBirthDepartment = getArguments().getInt(BIRTH_DEPARTMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerLabelTextView = (TextView) getView().findViewById(R.id.spinner_label);
        birthdayDatePicker = (DatePicker) getView().findViewById(R.id.date_picker);
        departmentBirthSpinner = (Spinner) getView().findViewById(R.id.spinner_birthday);
        confirm = (Button) getView().findViewById(R.id.Confirm_Date);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = birthdayDatePicker.getYear();
                int month = birthdayDatePicker.getMonth();
                int date = birthdayDatePicker.getDayOfMonth();
                String stringDate = String.format("%d-%d-%d", date, month + 1, year);
                mCallbackDate.setContextDate(name, firstName, stringDate,
                        departmentBirthSpinner.getSelectedItemPosition(),
                        birthCity);
            }
        });
        birthdayDatePicker.updateDate(getValuesFromDate(birthday)[0],
                getValuesFromDate(birthday)[1] - 1,
                getValuesFromDate(birthday)[2]);
        departmentBirthSpinner.setSelection(indexBirthDepartment);
    }

    private int[] getValuesFromDate(String date) {
        if (date.length() == 0) {
            String[] result = new SimpleDateFormat("dd-MM-yyyy").
                    format(Calendar.getInstance().getTime()).
                    split("-");
            return new int[]{Integer.parseInt(result[2]), Integer.parseInt(result[1]), Integer.parseInt(result[0])};
        }
        System.out.println(date);
        String[] result = date.split("-");
        return new int[]{Integer.parseInt(result[2]), Integer.parseInt(result[1]), Integer.parseInt(result[0])};
    }
}