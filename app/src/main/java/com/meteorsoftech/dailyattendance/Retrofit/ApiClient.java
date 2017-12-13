package com.meteorsoftech.dailyattendance.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dilip on 22-Nov-17.
 */

public class ApiClient {

  public static final String BASE_URL = "http://ghawadediilip.000webhostapp.com/Services/";
  public static Retrofit retrofit;

  public  static Retrofit getApiClient()
  {
    if (retrofit == null)
    {
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
  }

}
