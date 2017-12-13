package com.meteorsoftech.dailyattendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.meteorsoftech.dailyattendance.Fragment.Fragment_Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replacementFragment(new Fragment_Login(),true);

    }

    public void replacementFragment(Fragment fragment,boolean addToBack)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Main_Container,fragment,fragment.getClass().getName());
        if(addToBack)
        //ft.addToBackStack(null);
        ft.commit();
    }
}
