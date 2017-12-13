package com.meteorsoftech.dailyattendance.Retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.Retrofit.Model.Contact;

import java.util.List;

/**
 * Created by Dilip on 22-Nov-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
  List<Contact> contacts;

  public RecyclerViewAdapter (List<Contact>contacts)
  {
    this.contacts=contacts;
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_recyclerview_item,parent,false);


    return new MyViewHolder(v);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.textView.setText(contacts.get(position).getEmp_name());


  }

  @Override
  public int getItemCount() {
    return contacts.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    public MyViewHolder(View itemView) {
      super(itemView);

      textView = (TextView)itemView.findViewById(R.id.tv_emp_name);
    }



  }
}
