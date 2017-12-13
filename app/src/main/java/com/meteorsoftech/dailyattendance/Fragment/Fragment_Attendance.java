package com.meteorsoftech.dailyattendance.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.meteorsoftech.dailyattendance.Adapter.Get_NameAdapter;
import com.meteorsoftech.dailyattendance.Adapter.Recyclerview_Adapter;
import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.Retrofit.ApiClient;
import com.meteorsoftech.dailyattendance.Retrofit.ApiInterface;
import com.meteorsoftech.dailyattendance.Retrofit.Model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by Delete on 7/24/2017.
 */

public class Fragment_Attendance extends Fragment  {

    List<Contact> contacts;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    Recyclerview_Adapter recyclerview_adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance,container,false);


      contacts = new ArrayList<>();

      recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
      recyclerView.setHasFixedSize(false);
      recyclerViewlayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
      recyclerView.setLayoutManager(recyclerViewlayoutManager);

      final ProgressDialog  pd = new ProgressDialog(getContext());
      pd.setTitle("Fetching Data");
      pd.setCancelable(false);
      pd.show();
      ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
      retrofit2.Call<List<Contact>> call =apiInterface.getEmpName();
      call.enqueue(new Callback<List<Contact>>() {
        @Override
        public void onResponse(retrofit2.Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
          pd.dismiss();
          contacts = response.body();
          recyclerview_adapter = new Recyclerview_Adapter(contacts);
          recyclerView.setAdapter(recyclerview_adapter);
        }

        @Override
        public void onFailure(retrofit2.Call<List<Contact>> call, Throwable t) {

        }
      });
        return view;
    }



}
