package com.meteorsoftech.dailyattendance.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.Retrofit.ApiClient;
import com.meteorsoftech.dailyattendance.Retrofit.ApiInterface;
import com.meteorsoftech.dailyattendance.Retrofit.Model.Contact;
import com.meteorsoftech.dailyattendance.Retrofit.RecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Delete on 7/25/2017.
 */

public class Fragment_Emp_Report extends Fragment {

  RecyclerViewAdapter adapter;
  RecyclerView recyclerView;
  RecyclerView.LayoutManager layoutManager;
  List<Contact> contacts;
  ApiInterface apiInterface;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_emp_report, container, false);

    recyclerView = (RecyclerView) v.findViewById(R.id.report_recyclerview);
    layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    final ProgressDialog pd =new ProgressDialog(getContext());
    pd.setTitle("Fetching Data Wait");
    pd.setCancelable(false);
    pd.show();


    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    Call<List<Contact>> call = apiInterface.getEmpName();
    call.enqueue(new Callback<List<Contact>>() {
      @Override
      public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {

        pd.dismiss();
        contacts = response.body();
        adapter = new RecyclerViewAdapter(contacts);
        recyclerView.setAdapter(adapter);
      }

      @Override
      public void onFailure(Call<List<Contact>> call, Throwable t) {

      }
    });


    return v;
  }


}
