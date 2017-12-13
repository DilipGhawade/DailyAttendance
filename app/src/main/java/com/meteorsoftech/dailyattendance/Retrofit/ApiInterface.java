package com.meteorsoftech.dailyattendance.Retrofit;

import com.meteorsoftech.dailyattendance.Retrofit.Model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Dilip on 22-Nov-17.
 */

public interface ApiInterface {

  @POST("jsonFetchName.php")
  Call<List<Contact>> getEmpName();

}
