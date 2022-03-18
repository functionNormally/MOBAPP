package com.example.secondapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ShowFragment extends Fragment {


    private TextView theName;
    private TextView theFirstName;
    private TextView theBirthday;
    private TextView theBirthCity;
    private TextView theBirthDepartment;
    private static final String ARG_PARAM = "msg";
    private ArrayList<String> paramMsg;


    public static ShowFragment newInstance(ArrayList<String> param) {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramMsg = getArguments().getStringArrayList(ARG_PARAM);
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println(paramMsg.toString());
        theName = (TextView) getView().findViewById(R.id.name);
        theName.setText("Name: "+paramMsg.get(paramMsg.size()-5));
        theFirstName = (TextView) getView().findViewById(R.id.firstname);
        theFirstName.setText("First Name: "+paramMsg.get(paramMsg.size()-4));
        theBirthday = (TextView) getView().findViewById(R.id.birthday);
        theBirthday.setText("Birthday: "+paramMsg.get(paramMsg.size()-3));
        theBirthCity = (TextView) getView().findViewById(R.id.birthcity);
        theBirthCity.setText("Birth Department: "+paramMsg.get(paramMsg.size()-2));
        theBirthDepartment = (TextView) getView().findViewById(R.id.birth_department);
        theBirthDepartment.setText("Birth City: "+paramMsg.get(paramMsg.size()-1));
        for(int i=0;i<paramMsg.size()-5;i++){
            LinearLayout layout = (LinearLayout) getView().findViewById(R.id.displaylayout);
            LinearLayout NumberLayout = new LinearLayout(getActivity());
            NumberLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            NumberLayout.setOrientation(LinearLayout.VERTICAL);
            TextView shownumber = new TextView(getActivity());
            shownumber.setText("Telephone number: "+paramMsg.get(i));
            NumberLayout.addView(shownumber);
            layout.addView(NumberLayout);
        }
    }



}