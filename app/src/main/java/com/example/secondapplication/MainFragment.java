package com.example.secondapplication;

import static android.content.Intent.ACTION_VIEW;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainFragment extends Fragment {

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

    private Button Validate;
    private Button Add;
    private Button editButton;
    private EditText inputTelephoneNumber;
    private EditText inputName;
    private EditText inputFirstName;
    private EditText inputBirthday;
    private EditText inputBirthDepartment;
    private Spinner spinnerDepartments;
    private ArrayList<String> sum;
    private MenuInflater inflater;

    public MainFragment() {

    }

    public static MainFragment newInstance(String paramName, String paramFirstName, String paramBirthday, int paramIndexDepartment, String paramBirthCity) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(NAME, paramName);
        args.putString(FIRST_NAME, paramFirstName);
        args.putString(BIRTHDAY, paramBirthday);
        args.putInt(BIRTH_DEPARTMENT, paramIndexDepartment);
        args.putString(BIRTH_CITY, paramBirthCity);
        mainFragment.setArguments(args);
        return mainFragment;
    }


    public interface OnUpdateContentListener {
        void setContext(ArrayList<String> input);
    }

    public interface OnUpdateContentListenerEdit {
        void setContextEditFragment(String inputName, String inputFirstName, String inputBirthday, int inputBirthDepartment, String inputBirthCity);
    }

    OnUpdateContentListener mCallback;
    OnUpdateContentListenerEdit mCallbackEdit;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnUpdateContentListener) activity;
            mCallbackEdit = (OnUpdateContentListenerEdit) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            firstName = getArguments().getString(FIRST_NAME);
            birthCity = getArguments().getString(BIRTH_CITY);
            birthday = getArguments().getString(BIRTHDAY);
            indexBirthDepartment = getArguments().getInt(BIRTH_DEPARTMENT);
        }
        setRetainInstance(true);
        setHasOptionsMenu(true);


        //MenuItem item = (MenuItem) getView().findViewById(R.id.resetAction);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sum = new ArrayList<>();
        spinnerDepartments = (Spinner) getView().findViewById(R.id.Spinner);
        inputName = (EditText) getView().findViewById(R.id.name_edit_text);
        inputFirstName = (EditText) getView().findViewById(R.id.firstname_edit_text);
        inputBirthday = (EditText) getView().findViewById(R.id.birthday_edit_text);
        inputBirthDepartment = (EditText) getView().findViewById(R.id.birth_city_edit_view);

        if (getArguments() != null) {
            inputName.setText(name);
            inputFirstName.setText(firstName);
            inputBirthDepartment.setText(birthCity);
            inputBirthday.setText(birthday);
            spinnerDepartments.setSelection(indexBirthDepartment);
        }

        Validate = (Button) getView().findViewById(R.id.button_validation);
        Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });

        editButton = (Button) getView().findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbackEdit.setContextEditFragment(inputName.getText().toString(),
                        inputFirstName.getText().toString(),
                        inputBirthday.getText().toString(),
                        spinnerDepartments.getSelectedItemPosition(),
                        inputBirthDepartment.getText().toString());
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        final MenuItem reset = menu.add("Reset").setVisible(true);
        final MenuItem search = menu.add("Search").setVisible(true);
        final MenuItem share = menu.add("Share").setVisible(true);
        reset.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                reset();
                return false;
            }
        });
        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                search();
                return false;
            }
        });
        share.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                share();
                return false;
            }
        });
    }





    public void Validate() {
        sum.add(inputName.getText().toString());
        sum.add(inputFirstName.getText().toString());
        sum.add(inputBirthday.getText().toString());
        sum.add(inputBirthDepartment.getText().toString());
        sum.add(spinnerDepartments.getSelectedItem().toString());
        System.out.println(sum.toString());
        clickBtnValidate();

    }
    public void reset() {
        inputName.setText("");
        inputFirstName.setText("");
        inputBirthday.setText("");
        inputBirthDepartment.setText("");
        inputTelephoneNumber.setText("");
        spinnerDepartments.setSelection(0);
    }

    public void search() {
        Intent intent = new Intent();
        intent.setData(Uri.parse( "http://fr.wikipedia.org/?search="+inputBirthDepartment.getText().toString()));
        intent.setAction(ACTION_VIEW);
        this.startActivity(intent);
    }

    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, inputBirthDepartment.getText().toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    private void clickBtnValidate() {
        mCallback.setContext(sum);
    }

    public void updateBirthdayAndDepartment(String returnBirthday, int returnBirthDepartment) {
        System.out.println("UPDATE");
        inputBirthday.setText(returnBirthday);
        spinnerDepartments.setSelection(returnBirthDepartment);
        System.out.println("UPDATE");
    }

}
