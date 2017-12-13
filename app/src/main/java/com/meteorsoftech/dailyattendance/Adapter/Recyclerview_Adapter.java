package com.meteorsoftech.dailyattendance.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.Retrofit.Model.Contact;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Delete on 7/24/2017.
 */

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.ViewHolder> implements View.OnClickListener{

    Context context;
  List<Contact> contacts;
    String  Name,todayDate,Presenty;

    public Recyclerview_Adapter (List<Contact> contacts)
    {
        super();
        this.contacts=contacts;
        this.context=context;

    }



  @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
   /* public void add(int position,String item)
    {
      get_nameAdapter.add(position,item);
      notifyItemInserted(position);
    }*/

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.NameTextView.setText(contact.getEmp_name());
        //holder.spinner.setOnItemSelectedListener(this);
        //holder.button_ok.setOnClickListener(this);
        //holder.textView_fine.setOnClickListener(this);



    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      public TextView NameTextView;

      public ViewHolder(View itemView) {
        super(itemView);

        NameTextView = (TextView) itemView.findViewById(R.id.tv_empname);

      }
    }
}


