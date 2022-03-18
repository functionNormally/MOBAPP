package com.example.secondapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnUpdateContentListener, MainFragment.OnUpdateContentListenerEdit, EditFragment.OnUpdateContentListenerDate{

    protected FragmentManager fragmentManager = getSupportFragmentManager();
    protected FragmentTransaction tx = fragmentManager.beginTransaction();
    protected int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index");
        }
        if (index == 0){
            addFragment();
        }
    }

    private void addFragment() {
        MainFragment mainFragment = new MainFragment();
        tx.replace(R.id.main_layout, mainFragment, "main_tag");
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public void setContext(ArrayList<String> input) {
        ShowFragment showFragment = ShowFragment.newInstance(input);
        FragmentTransaction tx2 = fragmentManager.beginTransaction();
        tx2.replace(R.id.main_layout, showFragment, "show_tag");
        tx2.addToBackStack(null);
        tx2.commit();
    }

    @Override
    public void setContextEditFragment(String inputName, String inputFirstName, String inputBirthday, int inputBirthDepartment, String inputBirthCity) {
        EditFragment editFragment = EditFragment.newInstance(inputName, inputFirstName, inputBirthday, inputBirthDepartment, inputBirthCity);
        FragmentTransaction tx3 = fragmentManager.beginTransaction();
        tx3.replace(R.id.main_layout, editFragment, "edit_tag");
        tx3.addToBackStack(null);
        tx3.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        index = 1;
        outState.putInt("index", index);
    }

    @Override
    public void setContextDate(String returnName, String returnFirstName, String returnBirthday, int returnBirthDepartment, String returnBirthCity) {
        MainFragment mainFragment = MainFragment.newInstance(returnName, returnFirstName, returnBirthday, returnBirthDepartment, returnBirthCity);
        FragmentTransaction tx4 = fragmentManager.beginTransaction();
        tx4.replace(R.id.main_layout, mainFragment, "main_tag");
        tx4.addToBackStack(null);
        tx4.commit();
    }

}