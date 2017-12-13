package com.meteorsoftech.dailyattendance.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meteorsoftech.dailyattendance.R;

/**
 * Created by Delete on 7/14/2017.
 */

public class Fragment_Home extends android.support.v4.app.Fragment implements View.OnClickListener {

    ImageView imageView,imageView_attendance,imageView_report,imageView_logout,imageView_fine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView)view.findViewById(R.id.img_register);
        imageView_attendance = (ImageView)view.findViewById(R.id.img_attendance);
        imageView_report = (ImageView)view.findViewById(R.id.report_img);
        imageView_logout = (ImageView)view.findViewById(R.id.img_logout);
        imageView_fine = (ImageView)view.findViewById(R.id.img_fine);

        imageView.setOnClickListener(this);
        imageView_attendance.setOnClickListener(this);
        imageView_report.setOnClickListener(this);
        imageView_logout.setOnClickListener(this);
        imageView_fine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.img_register:
                replacementFragment(new Fragment_Registration(),true);
                break;
            case R.id.img_attendance:
                replacementFragment(new Fragment_Attendance(),true);
                break;

            case R.id.report_img:
                replacementFragment(new Fragment_Emp_Report(),true);
                break;
            case R.id.img_logout:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Main_Container,new Fragment_Login());
                ft.commit();
                break;
            case R.id.img_fine:
                replacementFragment(new Fragment_Fine(),true);
                break;

        }

    }
    public void replacementFragment(Fragment fragment, boolean addToBack)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Main_Container,fragment,fragment.getClass().getName());
        if (addToBack)
        ft.addToBackStack(null);
        ft.commit();
    }
}
